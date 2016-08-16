'use strict';
angular.module('webApp')
        .factory('accountService', function (constantService, $resource) {
            return {
                crud: function () {
                    return $resource(constantService.baseUrl + '/account/:id');
                },
                list: function () {
                    return $resource(constantService.baseUrl + '/account/list', {}, {
                        get: {method: 'POST', isArray: true}
                    });
                },
                search: function () {
                    return $resource(constantService.baseUrl + '/account/search/fields');
                }
            };
        })
        .factory('Account', function (accountService, $q, messageService, $log) {

            var getList = function (query) {
                var deferred = $q.defer();
                accountService.list().get(query,
                        function (response) {
                            deferred.resolve(response);
                        },
                        function (error) {
                            messageService.error("Error when getting Account list", error);
                            deferred.reject(error);
                        });
                return deferred.promise;
            };

            var getSearchFields = function () {
                return accountService.search().query(
                        function (response) {},
                        function (error) {
                            messageService.error("Error when retrieving search fields");
                        });
            };

            var deleteEntity = function () {

            }

            return {
//                getEmpty: getEmptyAccount,
                list: getList,
//                getList: getAccountList,
//                create: createAccount,
                delete: deleteEntity,
//                update: updateAccount,
                searchFields: getSearchFields
            };
        });



