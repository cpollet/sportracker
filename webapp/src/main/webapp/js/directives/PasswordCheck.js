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

stDirectives.directive('stMatch', ['$log', function ($log) {
	return {
		require: 'ngModel',
		link: function (scope, elem, attrs, ctrl) {
			var me = attrs.ngModel;
			var matchTo = attrs.stMatch;

			scope.$watch(matchTo, function (value) {
				ctrl.$setValidity('match', scope[me] === scope[matchTo]);
			});

			scope.$watch(me, function (value) {
				ctrl.$setValidity('match', scope[me] === scope[matchTo]);
			});
		}
	};
}]);