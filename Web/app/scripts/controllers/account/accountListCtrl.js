'use strict';

/**
 * @ngdoc function
 * @name webApp.controller:AccountlistCtrl
 * @description
 * # AccountlistCtrl 
 * Controller of the webApp
 */
angular.module('webApp')
        .controller('AccountListCtrl', function ($scope, $accountService, $constantService, $uibModal, $log) {

            $scope.accounts = $accountService.getAll();
            $scope.initListSize = $constantService.defaultListFetchSize;
            $scope.currentListSize = $scope.initListSize;


            $scope.getMore = function () {
                if ($scope.initListSize === $scope.accounts.length) {
                    $accountService.getList($scope.currentListSize += $scope.initListSize);
                }
            };

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
