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
	stModules.factory('stHttpInterceptor', ['$location', 'localStorageService',
		function($location, localStorageService) {
			var responseInterceptor = {
				request: function(config) {
					var auth = localStorageService.get('auth');

					if (auth !== null) {
						config.headers['X-ST-Token'] = auth.token;
					}

					return config;
				},

				responseError: function(response) {
					if (response.data.httpStatus == 403) {
						localStorageService.remove('auth');
						$location.path("/");

						return response;
					}
				}
			};

			return responseInterceptor;
		}
	]);
})();