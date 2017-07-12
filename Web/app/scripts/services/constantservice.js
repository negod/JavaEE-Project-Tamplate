'use strict';

/**
 * @ngdoc service
 * @name webApp.ConstantService
 * @description
 * # ConstantService
 * Service in the webApp.
 */
angular.module('webApp')
        .service('ConstantService', function () {
            return{
                baseUrl: "http://localhost:29080/application/rest",
                messageTimeout: 4000,
                searchQuery: {
                    searchFields: [
                    ],
                    globalSearchWord: null,
                    pagination: {
                        listSize: 10,
                        page: 0
                    }
                }
            };
        });
