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

var stApp = angular.module('stApp', ['stControllers', 'stServices', 'stDirectives', 'ngRoute', 'ui.bootstrap', 'google-maps']);

stApp.config(['$routeProvider',
	function ($routeProvider) {
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
				controller: 'AuthenticationCtrl'
			}).
			when('/about', {
				templateUrl: 'partials/about.html'
			}).
			when('/track', {
				templateUrl: 'partials/track.html',
				controller: 'TrackCtrl'
			}).
			otherwise({
				redirectTo: '/home'
			});
	}
]);

var stControllers = angular.module('stControllers', []);
var stServices    = angular.module('stServices', []);
var stDirectives  = angular.module('stDirectives', []);