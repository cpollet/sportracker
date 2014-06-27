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

'use strict';

describe('Authentication', function() {
	var $location;
	var Authentication;

	var localStorageService = new function() {
		this.self = this;
		this.auth = null;

		this.setAuth = function(auth) {
			self.auth = auth;
		};

		this.get = function(key) {
			if (key == 'auth') {
				return self.auth;
			} else {
				return null;
			}
		};

		this.remove = function(key) {
			if (key == 'auth') {
				self.auth = null;
			}
		}
	};

	beforeEach(function() {
		module('stServices');

		module(function($provide) {
			$provide.value('localStorageService', localStorageService);
		});

		inject(function($injector) {
			Authentication = $injector.get('Authentication');
			$location = $injector.get('$location');
		});
	});

	describe('isLogged()', function() {

		it('should return false when no auth exists', function() {
			localStorageService.setAuth(null);
			expect(Authentication.isLogged()).toBeFalsy();
		});

		it('should return false when an auth exists with isLogged=false', function() {
			localStorageService.setAuth({ isLogged: false });
			expect(Authentication.isLogged()).toBeFalsy();
		});

		it('should return true when an auth exists and isLogged=true', function() {
			localStorageService.setAuth({ isLogged: true });
			expect(Authentication.isLogged()).toBeTruthy();
		})
	});

	describe('getUserId()', function() {
		it('returns null when not logged', function() {
			localStorageService.setAuth({ isLogged: false, userId: 'userId' });
			expect(Authentication.getUserId()).toBeNull();
		});

		it('returns userid when logged', function() {
			localStorageService.setAuth({ isLogged: true, userId: 'userId' });
			expect(Authentication.getUserId()).toBe('userId');
		});
	});

	describe('getUsername()', function() {
		it('returns null when not logged', function() {
			localStorageService.setAuth({ isLogged: false, username: 'username' });
			expect(Authentication.getUsername()).toBeNull();
		});

		it('returns username when logged', function() {
			localStorageService.setAuth({ isLogged: true, username: 'username' });
			expect(Authentication.getUsername()).toBe('username');
		});
	});

	describe('logout()', function() {
		it('destroys auth', function() {
			localStorageService.setAuth({ isLogged: true, username: 'username' });
			Authentication.logout();
			expect(Authentication.isLogged()).toBeFalsy();
		});

		it('redirects to /', function() {
			spyOn($location, 'path');

			Authentication.logout();
			expect().toBeFalsy();

			expect($location.path).toHaveBeenCalledWith('/');
		});
	});

});