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
	stServices.factory('Person', ['$resource', '$location', 'Authentication',
		function ($resource, $location, Authentication) {
			function defaultParams() {
				return {
					userId: function() {
						return Authentication.getUserId();
					}
				};
			}

			return $resource('/api/v1/users/:userId/person', { userId: '@userId' }, {
				get: { method: 'GET', isArray: false, params: defaultParams() },
				save: { method: 'PUT', isArray: false, params: defaultParams() }
			});
		}
	]);
})();