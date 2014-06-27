/*
 * Copyright 2014 Christophe Pollet
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
(function () {
	'use strict';
	stServices.factory('Authentication', ['$location', '$q', 'Token', 'localStorageService',
		function ($location, $q, Token, localStorageService) {
			var Authentication = {
				isLogged: function () {
					var auth = this._getAuth();

					if (auth === null) {
						return false;
					}

					return auth.isLogged;
				},

				_getAuth: function() {
					return localStorageService.get('auth');
				},

				getUsername: function () {
					if (!this.isLogged()) {
						return null;
					}

					return this._getAuth().username;
				},

				getUserId: function() {
					if (!this.isLogged()) {
						return null;
					}

					return this._getAuth().userId;
				},

				login: function (username, password) {
					var result = $q.defer();

					Token.create({}, {
							username: username,
							password: password
						},
						function (value, responseHeaders) {

							localStorageService.add('auth', {
								isLogged: true,
								username: username,
								userId: value.object.userId,
								token: value.object.token
							});

							result.resolve("OK");
						},
						function (value, responseHeaders) {
							result.resolve(value.data.errorStatus);
						}
					);

					return result.promise;
				},

				restore: function () {
					var result = $q.defer();
					var auth = localStorageService.get('auth');

					if (auth === null) {
						result.resolve(false);
						return result.promise;
					}

					Token.query({
							username: auth.username,
							token: auth.token
						},
						function (value, responseHeaders) {
							result.resolve(true);
						},
						function (value, responseHeaders) {
							Authentication.logout();
							result.resolve(false);
						}
					);

					return result.promise;
				},

				logout: function () {
					localStorageService.remove('auth');
					$location.path("/");
				}
			};

			return Authentication;
		}
	]);
})();