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
	stDirectives.directive('stMatch', ['$log', function ($log) {
		return {
			require: 'ngModel',
			link: function (scope, elem, attrs, ctrl) {
				var me = attrs.ngModel;
				var matchTo = attrs.stMatch;

				function doesMatch(val1, val2) {
					return val1 === val2;
					// ctrl.$setValidity('dontMatch', match);
				}

				scope.$watch(matchTo, function (value) {
					var match = doesMatch(scope[me], scope[matchTo]);
					ctrl.$setValidity('dontMatch', match);
				});

				scope.$watch(me, function (value) {
					var match = doesMatch(scope[me], scope[matchTo]);
					ctrl.$setValidity('dontMatch', match);
				});
			}
		};
	}]);
})();