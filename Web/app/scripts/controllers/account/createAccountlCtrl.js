'use strict';

/**
 * @ngdoc function
 * @name webApp.controller:AccountCtrl
 * @description
 * # AccountCtrl
 * Controller of the webApp
 */
angular.module('webApp')
        .controller('CreateAccountCtrl', function ($scope, $accountService, messageService, $log, account) {

            $scope.selections = {
                addMore: true,
                edit: account !== undefined ? true : false,
                text: account !== undefined ? "Edit Account" : "Create new Account"
            };

            if ($scope.selections.edit === false) {
                $scope.account = $accountService.getEmpty();
            } else {
                $scope.account = account;
                $scope.oldAccount = angular.copy(account);
            }

            $scope.save = function () {

                var onError = function () {
                    if ($scope.selections.edit === true) {
                        messageService.error("Failed to update account");
                    } else {
                        messageService.error("Failed to create account");
                    }
                };

                var onSuccess = function () {

                    if ($scope.selections.edit === true) {
                        messageService.success("Acocunt updated");
                    } else {
                        $scope.account = $accountService.getEmpty();
                        messageService.success("Acocunt created");
                    }

                    if ($scope.selections.addMore === false || $scope.selections.edit === true) {
                        $scope.cancel();
                    }
                };

                if ($scope.selections.edit === true) {
                    $accountService.update($scope.account).then(onSuccess, onError);
                } else {
                    $accountService.create($scope.account).then(onSuccess, onError);
                }
            };

            $scope.cancel = function () {

                if ($scope.selections.edit === true) {
                    account = angular.copy($scope.oldAccount);
                }

                $scope.$dismiss('cancel');
            };


        });

