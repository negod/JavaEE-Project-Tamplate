'use strict';

angular.module('webApp')
        .factory('$accountService', function ($constantService, $http, messageService) {

            var accounts = [];

            //Callbacks
            var onError = function (data) {
                messageService.error("Failed to get list of accounts");
            };

            var onSuccess = function (data) {
                angular.copy(data, accounts);
            };

            var onDeleteSucces = function (data) {
                var index = accounts.indexOf(data);
                accounts.splice(index, 1);
                messageService.success("Account deleted");
            };

            var onDeleteError = function (data) {
                messageService.error("Failed to delete Account");
            };

            var onCreateSucces = function (response) {
                accounts.push(response.data);
                messageService.success("Account created");
            };

            var onCreateError = function (response) {
                messageService.error("Failed to create Account");
            };


            var getAccounts = function () {
                return accounts;
            };

            //Methods
            var createAccount = function (account) {
                return $http.post($constantService.baseUrl + "/account", account).then(function (response) {
                    accounts.push(response.data);
                });
            };

            var deleteAccount = function (account) {
                return $http.delete($constantService.baseUrl + "/account/" + account.id).then(function (response) {
                    return response;
                }).then(onDeleteSucces(account), onDeleteError);
            };

            var getAccoutsFromWS = function () {
                return $http.get($constantService.baseUrl + "/account").then(function (response) {
                    return response.data;
                }).then(onSuccess, onError);
            }

            getAccoutsFromWS();

            return {
                getAll: getAccounts,
                create: createAccount,
                delete: deleteAccount
            };

        });



