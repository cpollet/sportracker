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

stServices.factory('Authentication', ['$location',
	function ($location) {
		var _isLogged = false;
		var _username = null;

		var Authentication = {
			isLogged: function() {
				return _isLogged;
			},

			login: function(username) {
				_isLogged = true;
				_username = username;
				$location.path("/");
			},

			logout: function() {
				_isLogged = false;
				_username = null;
				$location.path("/");
			},

			getUsername: function() {
				return _username;
			}
		};

		return Authentication;
	}
]);