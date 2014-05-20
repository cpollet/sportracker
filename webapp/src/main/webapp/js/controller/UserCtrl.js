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
	stControllers.controller('UserCtrl', ['$scope', '$http', '$log', '$location', 'User',
		function ($scope, $http, $log, $location, User) {
			$scope.signup = function () {
				User.create({}, {
						username: $scope.username,
						password1: $scope.password1,
						password2: $scope.password2
					},
					function (value, responseHeaders) {
						$location.path("/signup_success");
					},
					function (value, responseHeaders) {
						if (value.data.errorStatus == 'UsernameNotAvailable') {
							$scope.signupForm.username.$setValidity("notAvailable", false);
						}
					}
				);
			};
		}
	]);
})();