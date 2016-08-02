'use strict';

angular.module('webApp')
        .factory('$accountService', function ($constantService, $http, $log) {

            var accounts = [];

            var onError = function (data) {
                $log.error("Failed to get list of accounts");
            };

            var onSuccess = function (data) {
                angular.copy(data, accounts);
            };

            var getAccounts = function () {
                return accounts;
            }

            var createAccount = function (account) {
                return $http.post($constantService.baseUrl + "/account", account).then(function (response) {
                    accounts.push(response.data);
                });
            };

            var getAccoutsFromWS = function () {
                return $http.get($constantService.baseUrl + "/account").then(function (response) {
                    return response.data;
                }).then(onSuccess, onError);
            }

            getAccoutsFromWS();

            return {
                getAll: getAccounts,
                create: createAccount
            };

        });



