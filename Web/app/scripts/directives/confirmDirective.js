'use strict';

/**
 * @ngdoc directive
 * @name webApp.directive:test
 * @description
 * # test
 */
angular.module('webApp')
        .directive('confirm', function (ConfirmService) {
            return {
                restrict: 'A',
                scope: {
                    eventHandler: '&ngClick'
                },
                link: function (scope, element, attrs) {
                    element.unbind("click");
                    element.bind("click", function (e) {
                        ConfirmService.open(attrs.confirm, scope.eventHandler);
                    });
                }
            };
        });
