'use strict';

angular.module('webApp')
        .factory('$accountService', function ($constantService, $http) {

            var getAccounts = function () {
                return $http.get($constantService.baseUrl + "/account").then(function (response) {
                    return response.data;
                });
            };

            var createAccount = function (account) {
                return $http.post($constantService.baseUrl + "/account", account).then(function (response) {
                    return response.data;
                });
            };

            return {
                getAll: getAccounts,
                create: createAccount
            };

        });



