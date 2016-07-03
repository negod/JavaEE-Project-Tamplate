'use strict';

/**
 * @ngdoc function
 * @name webApp.controller:AccountCtrl
 * @description
 * # AccountCtrl
 * Controller of the webApp
 */
angular.module('webApp')
        .controller('CreateAccountCtrl', function ($scope, $accountService, messageService) {

            var initAccount = function () {
                $scope.account = {
                    name: ""
                };
            }

            initAccount();

            $scope.save = function (account) {

                var onError = function (reason) {
                    messageService.error("Failed to save acocunt");
                };

                var onSuccess = function (response) {
                    initAccount();
                    messageService.success("Account saved");
                };

                $accountService.create($scope.account).then(onSuccess, onError);

            };
        });

