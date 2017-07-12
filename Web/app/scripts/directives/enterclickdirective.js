'use strict';

/**
 * @ngdoc directive
 * @name webApp.directive:EnterClickDirective
 * @description
 * # EnterClickDirective
 */
angular.module('webApp').directive('onEnterClick', function () {
    return function (scope, element, attrs) {
        element.bind("keydown keypress", function (event) {
            if (event.which === 13) {
                scope.$apply(function () {
                    scope.$eval(attrs.onEnterClick);
                });
                event.preventDefault();
            }
        });
    };
});
