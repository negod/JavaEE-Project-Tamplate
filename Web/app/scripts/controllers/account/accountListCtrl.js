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

            $scope.showModal = function (size) {

                var modalInstance = $uibModal.open({
                    animation: $scope.animationsEnabled,
                    templateUrl: '/views/modal/accountModal.html',
                    controller: 'CreateAccountModalCtrl',
                    size: size,
                    resolve: {
                        accounts: function () {
                            return $scope.accounts;
                        }
                    }
                });

                modalInstance.result.then(function () {
                    accounts = $accountService.accounts;
                }, function () {
                    $log.info('Modal dismissed at: ' + new Date());
                });
            };

        });