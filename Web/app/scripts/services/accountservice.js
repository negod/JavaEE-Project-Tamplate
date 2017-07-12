'use strict';

/**
 * @ngdoc service
 * @name webApp.AccountService
 * @description
 * # AccountService
 * Service in the webApp.
 */
angular.module('webApp')
        .service('AccountService', function (CrudService, $q, messageService, $log) {
            return{
                list: function (query) {
                    var deferred = $q.defer();
                    CrudService.list().get(query,
                            function (response) {
                                deferred.resolve(response);
                            },
                            function (error) {
                                messageService.error("Error when getting Account list", error);
                                deferred.reject(error);
                            });
                    return deferred.promise;
                },
                searchFields: function () {
                    var deferred = $q.defer();
                    return CrudService.search().query(
                            function (response) {

                            },
                            function (error) {
                                messageService.error("Error when retrieving search fields");
                            });
                },
                delete: function () {

                }
            }

//            var getList = function (query) {
//                var deferred = $q.defer();
//                accountService.list().get(query,
//                        function (response) {
//                            deferred.resolve(response);
//                        },
//                        function (error) {
//                            messageService.error("Error when getting Account list", error);
//                            deferred.reject(error);
//                        });
//                return deferred.promise;
//            };

//            var getSearchFields = function () {
//                return accountService.search().query(
//                        function (response) {},
//                        function (error) {
//                            messageService.error("Error when retrieving search fields");
//                        });
//            };
//
//            var deleteEntity = function () {
//
//            }

//            return {
////                getEmpty: getEmptyAccount,
//                list: getList,
////                getList: getAccountList,
////                create: createAccount,
//                delete: deleteEntity,
////                update: updateAccount,
//                searchFields: getSearchFields
//            };
        });
