'use strict';

/**
 * @ngdoc function
 * @name webApp.controller:AccountlistCtrl
 * @description
 * # AccountlistCtrl 
 * Controller of the webApp
 */
angular.module('webApp')
        .controller('AccountListCtrl', function ($scope, $accountService) {

            var onError = function (data) {
                $scope.error = "Failed to get list of accounts";
            };

            var onSuccess = function (data) {
                $scope.accounts = data;
            };

            $accountService.getAll().then(onSuccess, onError);

        });
