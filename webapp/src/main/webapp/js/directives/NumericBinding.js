/**
 * Created by cpollet on 23.06.14.
 */
(function () {
	'use strict';
	stDirectives.directive('numericbinding', function () {
		return {
			restrict: 'A',
			require: 'ngModel',
			scope: {
				model: '=ngModel',
			},
			link: function (scope, element, attrs, ngModelCtrl) {
				if (scope.model && typeof scope.model == 'string') {
					scope.model = parseInt(scope.model);
				}
			}
		};
	});
})();