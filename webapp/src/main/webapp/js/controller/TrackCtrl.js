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
	stControllers.controller('TrackCtrl', ['$scope', '$http',
		function ($scope, $http) {
			$scope.filePath = '/Users/cpollet/Downloads/sample.fit';

			$scope.map = {
				center: {
					latitude: 46.2,
					longitude: 6.15
				},
				options: {
					mapTypeId: google.maps.MapTypeId.TERRAIN
				},
				zoom: 12,
				events: {
					tilesloaded: function (mapInstance) {
						$scope.$apply(function () {
							$scope.mapInstance = mapInstance;
						});
					}
				}
			};

			$scope.updateRoute = function () {
				$http.get('api/v1/track?filePath=' + $scope.filePath)
					.success(function (data) {
						$scope.track = data;

						var trackPathCoordinates = [];

						for (var i = 0; i < data['trackPoints'].length; i++) {
							var latLng = new google.maps.LatLng(
								data['trackPoints'][i]['latitude'],
								data['trackPoints'][i]['longitude']
							);
							trackPathCoordinates.push(latLng);
						}

						var trackPath = new google.maps.Polyline({
							path: trackPathCoordinates,
							geodesic: true,
							strokeColor: '#FF0000',
							strokeOpacity: 1.0,
							strokeWeight: 2
						});

						trackPath.setMap($scope.mapInstance);
					});
			};

			$scope.updateMap = function () {
				window.setTimeout(function () {
					google.maps.event.trigger($scope.mapInstance, 'resize');
					$scope.mapInstance.setCenter(new google.maps.LatLng($scope.map.center.latitude, $scope.map.center.longitude));
				}, 100);
			}
		}
	]);
})();