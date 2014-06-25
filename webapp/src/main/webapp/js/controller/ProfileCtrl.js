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
	stControllers.controller('ProfileCtrl', ['$scope', '$log', 'Person',
		function ($scope, $log, Person) {
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
					minHeartRate: {
						unit: '1/min'
					},
					maxHeartRate: {
						unit: '1/min'
					}
				}
			};

			$scope.$watch("profile.person.maxHeartRateMethod", function (newValue, oldValue) {
				computeMaxHeartRateIfNeeded();
			});

			$scope.$watch("profile.person.birthday", function (newValue, oldValue) {
				computeMaxHeartRateIfNeeded();
			});

			$scope.save = function() {
				Person.save($scope.profile.person);
			};

			var person = Person.get();
			person.$promise.then(function() {
				var personObject = person.object;

				if (personObject !== null) {
					personObject.birthday = new Date(personObject.birthday);

					$scope.profile.person = personObject;
				}
			});

			function computeMaxHeartRateIfNeeded() {
				if ($scope.profile.person && $scope.profile.person.maxHeartRateMethod == 'AUTO') {
					$scope.profile.person.maxHeartRate.value = 220 - Math.floor((new Date() - $scope.profile.person.birthday) / (1000 * 60 * 60 * 24 * 365));
				}
			}

		}
	]);
})();