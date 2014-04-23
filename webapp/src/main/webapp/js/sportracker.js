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

var stApp = angular.module('stApp', [
	'stControllers', 'stServices', 'stDirectives',
	'ngRoute',
	'ui.bootstrap', 'google-maps',
	'LocalStorageModule'
]);

stApp.config(['$routeProvider', '$httpProvider',
	function ($routeProvider, $httpProvider) {
		$routeProvider.
			when('/home', {
				templateUrl: 'partials/home.html'
			}).
			when('/login', {
				templateUrl: 'partials/login.html',
				controller: 'AuthenticationCtrl'
			}).
			when('/signup', {
				templateUrl: 'partials/signup.html',
				controller: 'UserCtrl'
			}).
			when('/signup_success', {
				templateUrl: 'partials/signup_success.html'
			}).
			when('/about', {
				templateUrl: 'partials/about.html'
			}).
			when('/track', {
				templateUrl: 'partials/track.html',
				controller: 'TrackCtrl',
				access: {
					requiresLogin: true
				}
			}).
			otherwise({
				redirectTo: '/home'
			});
	}
]);

stApp.run(['$rootScope', '$location', '$log', 'Authentication', 'localStorageService',
	function ($rootScope, $location, $log, Authentication, localStorageService) {
		$rootScope.$watch(
			function () {
				return Authentication.isLogged();
			},
			function (newValue) {
				$rootScope.isLogged = newValue;
			}
		);
		$rootScope.$watch(
			function () {
				return Authentication.getUsername()
			},
			function (newValue) {
				$rootScope.username = newValue;
			}
		);
		$rootScope.logout = Authentication.logout;

		$rootScope.$on('$routeChangeStart', function (event, next) {
			// FIXME: this does not prevent the template to be loaded
			if (next.access && next.access.requiresLogin && !Authentication.isLogged()) {
				$location.url("/login");
				event.preventDefault();
			}
		});

		localStorageService.prefix = 'st';
		Authentication.restore().then(
			function (result) {
				if (result) {
					$location.path("/track");
				}
			}
		);
	}
]);

var stControllers = angular.module('stControllers', []);
var stServices = angular.module('stServices', ['ngResource']);
var stDirectives = angular.module('stDirectives', []);