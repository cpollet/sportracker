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
	stControllers.controller('AuthenticationCtrl', ['$scope', '$http', '$log', '$location', 'User', 'Authentication',
		function ($scope, $http, $log, $location, User, Authentication) {
			$scope.login = function () {
				Authentication.login($scope.username, $scope.password).then(
					function (result) {
						if (result == 'OK') {
							$location.path("/profile");
						} else {
							$scope.password = '';
							$scope.invalidCredentials = true;
						}
					}
				);
			};
		}
	]);
})();