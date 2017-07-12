'use strict';

/**
 * @ngdoc service
 * @name webApp.CrudService
 * @description
 * # CrudService
 * Service in the webApp.
 */
angular.module('webApp')
        .service('CrudService', function (ConstantService, $resource) {
            return {
                crud: function () {
                    return $resource(ConstantService.baseUrl + '/account/:id');
                },
                list: function () {
                    return $resource(ConstantService.baseUrl + '/account/list', {}, {
                        get: {method: 'POST', isArray: true}
                    });
                },
                search: function () {
                    return $resource(ConstantService.baseUrl + '/account/search/fields');
                }
            };
        });
