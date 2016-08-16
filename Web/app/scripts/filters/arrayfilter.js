'use strict';

/**
 * @ngdoc filter
 * @name webApp.filter:ArrayFilter
 * @function
 * @description
 * # ArrayFilter
 * Filter in the webApp.
 */
angular.module('webApp')
        .filter('ArrayFilter', function () {
            return function (value) {
                if (!angular.isArray(value))
                    return '';
                return value.join(', ');
            };
        });
