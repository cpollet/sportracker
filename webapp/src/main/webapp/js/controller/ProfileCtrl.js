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
	stControllers.controller('ProfileCtrl', ['$scope', '$log',
		function ($scope, $log) {
			$scope.profile = {
				ui: {
					tab: {
						active: 'general'
					},
					datepicker: {
						opened: false,
						dateOptions: {
							'year-format': "'yy'",
							'starting-day': 1
						},
						open: function ($event) {
							$event.preventDefault();
							$event.stopPropagation();

							$scope.profile.ui.datepicker.opened = !$scope.profile.ui.datepicker.opened;
						}
					}
				},
				person: {
					heartRate: {
						min: null,
						max: null,
						method: null
					},
					weight: {
						value: null,
						unit: 'kg'
					},
					height: {
						value: null,
						unit: 'cm'
					},
					birthDate: new Date(),
					gender: null
				}
			};

			function computeMaxHeartRateIfNeeded() {
				if ($scope.profile.person.heartRate.method == 'auto') {
					$scope.profile.person.heartRate.max = 220 - Math.floor((new Date() - $scope.profile.person.birthDate) / (1000 * 60 * 60 * 24 * 365));
				}
			}

			$scope.$watch("profile.person.heartRate.method", function (newValue, oldValue) {
				computeMaxHeartRateIfNeeded();
			});

			$scope.$watch("profile.person.birthDate", function (newValue, oldValue) {
				computeMaxHeartRateIfNeeded();
			});
		}
	]);
})();