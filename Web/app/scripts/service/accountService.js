'use strict';

angular.module('webApp')
        .factory('$accountService', function ($constantService, $http, $log) {

            var accounts = {};

            accounts.list = [];

            accounts.add = function (account) {
                accounts.list.push(account);
            }

            var onError = function (data) {
                $log.error("Failed to get list of accounts");
            };

            var onSuccess = function (data) {
                accounts.list = data;
            };

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

            getAccounts().then(onSuccess, onError);

            return {
                accounts: accounts,
                getAll: getAccounts,
                create: createAccount
            };

        });



