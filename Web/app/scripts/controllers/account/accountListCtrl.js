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

            $scope.searchQuery = angular.copy($constantService.searchQuery);
            var initListSize = $scope.searchQuery.pagination.listSize;
            $scope.searchQuery.searchFields = $accountService.searchFields();

            $scope.getMore = function () {
                if ($scope.searchQuery.pagination.listSize === $scope.accounts.length) {
                    $scope.searchQuery.pagination.listSize += initListSize;
                    $accountService.getList($scope.searchQuery);
                }
            };

            $scope.search = function () {
                $accountService.getList($scope.searchQuery);
                $scope.accounts = $accountService.getAll();
            }

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
