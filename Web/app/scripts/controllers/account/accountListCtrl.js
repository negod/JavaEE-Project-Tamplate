'use strict';

/**
 * @ngdoc function
 * @name webApp.controller:AccountlistCtrl
 * @description
 * # AccountlistCtrl 
 * Controller of the webApp
 */
angular.module('webApp')
        .controller('AccountListCtrl', function ($scope, $accountService, $uibModal, $log) {

            $scope.accounts = $accountService.getAll();

            $scope.deleteAccount = function (account) {
                $accountService.delete(account);
            };

            $scope.showModal = function (size, account) {

                var modalInstance = $uibModal.open({
                    animation: $scope.animationsEnabled,
                    templateUrl: 'views/account/createAccount.html',
                    controller: 'CreateAccountCtrl',
                    backdrop: 'static',
                    size: size,
                    resolve: {
                        account: function () {
                            return account;
                        }
                    }
                });

                modalInstance.result.then(function () {
                }, function () {
                    //$log.info('Modal dismissed at: ' + new Date());
                });
            };

        });
