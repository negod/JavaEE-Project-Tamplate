'use strict';

angular.module('webApp')
        .factory('$accountService', function ($constantService, $http, messageService, $log) {

            var accounts = [];
            var searchFields = [];

            var getEmptyAccount = function () {
                var account = {
                    name: ""
                };
                return account;
            };

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

            var onSearchFieldSucces = function (data) {
                angular.copy(data, searchFields);
            };

            var onSearchFieldErrors = function (response) {
                messageService.error("Failed to retrieve searchfields");
                $log.info("Retrieval of searchfields error");
            };


            var getAccounts = function () {
                return accounts;
            };

            var getSearchFields = function () {
                return searchFields;
            };

            //Methods
            var createAccount = function (account) {
                return $http.post($constantService.baseUrl + "/account", account).then(function (response) {
                    accounts.push(response.data);
                });
            };

            var updateAccount = function (account) {
                return $http.put($constantService.baseUrl + "/account", account).then(function (response) {
                    var index = accounts.indexOf(account);
                    accounts[index] = response.data;
                });
            };

            var deleteAccount = function (account) {
                return $http.delete($constantService.baseUrl + "/account/" + account.id).then(function (response) {
                    return response;
                }).then(onDeleteSucces(account), onDeleteError);
            };

            var getAccountList = function (query) {
                return $http.post($constantService.baseUrl + "/account/list", query).then(function (response) {
                    return response.data;
                }).then(onSuccess, onError);
            };

            var getSearchFieldList = function () {
                return $http.get($constantService.baseUrl + "/account/searchfields").then(function (response) {
                    return response.data;
                }).then(onSearchFieldSucces, onSearchFieldErrors);
            };

            getAccountList($constantService.searchQuery);
            getSearchFieldList();

            return {
                getEmpty: getEmptyAccount,
                getAll: getAccounts,
                getList: getAccountList,
                create: createAccount,
                delete: deleteAccount,
                update: updateAccount,
                searchFields: getSearchFields
            };

        });



