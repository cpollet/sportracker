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
(function() {
'use strict';
stServices.factory('Authentication', ['$location', '$log', '$q', 'Session', 'localStorageService',
	function ($location, $log, $q, Session, localStorageService) {
		var _isLogged = false;
		var _token = null;
		var _username = null;

		var Authentication = {
			isLogged: function () {
				return _isLogged;
			},

			getUsername: function () {
				return _username;
			},

			login: function (username, password) {
				var result = $q.defer();

				Session.create({}, {
						username: username,
						password: password
					},
					function (value, responseHeaders) {
						_isLogged = true;
						_username = username;

						localStorageService.add('auth', {
							username: _username,
							token: value.object.token
						});

						result.resolve("OK");
					},
					function (value, responseHeaders) {
						$log.info(value);

						result.resolve(value.data.errorStatus);
					}
				);

				return result.promise;
			},

			restore: function() {
				var result = $q.defer();
				var auth = localStorageService.get('auth');

				if (auth === null) {
					result.resolve(false);
					return result.promise;
				}

				Session.query({
					username: auth.username,
					token: auth.token
				},
				function(value, responseHeaders) {
					_isLogged = true;
					_username = auth.username;
					result.resolve(true);
				},
				function(value, responseHeaders) {
					Authentication.logout();
					result.resolve(false);
				});

				return result.promise;
			},

			logout: function () {
				_isLogged = false;
				_username = null;

				localStorageService.remove('auth');

				$location.path("/");
			}
		};

		return Authentication;
	}
]);
})();