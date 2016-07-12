'use strict';

/**
 * @ngdoc function
 * @name webApp.controller:AccountCtrl
 * @description
 * # AccountCtrl
 * Controller of the webApp
 */
angular.module('webApp')
        .controller('CreateAccountModalCtrl', function ($scope, $accountService, $uibModal, $log, messageService) {

            var initAccount = function () {
                $scope.account = {
                    name: ""
                };
            }

            initAccount();

            $scope.save = function () {

                var onError = function (reason) {
                    messageService.error("Failed to save acocunt");
                };

                var onSuccess = function (response, data) {
                    $log.info(response);
                    initAccount();
                    $accountService.accounts.add(response);
                    messageService.success("Account saved");
                };

                $accountService.create($scope.account).then(onSuccess, onError);

            };

        });

