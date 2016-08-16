'use strict';

/**
 * @ngdoc function
 * @name webApp.controller:AccountlistCtrl
 * @description
 * # AccountlistCtrl 
 * Controller of the webApp
 */
angular.module('webApp')
        .controller('AccountListCtrl', function ($scope, Account, constantService, $uibModal, $log) {

            $scope.searchQuery = angular.copy(constantService.searchQuery);
            var initListSize = $scope.searchQuery.pagination.listSize;
            $scope.searchQuery.searchFields = Account.searchFields();

            $scope.accounts = [];

            $scope.getAccounts = function (query) {
                Account.list(query).then(
                        function (response) {
                            response.forEach(function (entry) {
                                $scope.accounts.push(entry);
                            });
                        }
                );
            };
            $scope.getAccounts($scope.searchQuery);

            $scope.getMore = function () {
                var listSize = $scope.searchQuery.pagination.listSize;
                var page = $scope.searchQuery.pagination.page + 1;
                if ((listSize * page) === $scope.accounts.length) {
                    $scope.searchQuery.pagination.page += 1;
                    $scope.getAccounts($scope.searchQuery);
                }
            };

            $scope.search = function () {
                $scope.searchQuery.pagination.listSize = initListSize;
                $scope.searchQuery.pagination.page = 0;
                $scope.accounts = [];
                $scope.getAccounts($scope.searchQuery);
            }

            $scope.deleteAccount = function (account) {
                Account.delete(account);
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
