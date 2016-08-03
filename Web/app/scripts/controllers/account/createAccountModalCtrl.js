'use strict';

/**
 * @ngdoc function
 * @name webApp.controller:AccountCtrl
 * @description
 * # AccountCtrl
 * Controller of the webApp
 */
angular.module('webApp')
        .controller('CreateAccountModalCtrl', function ($scope, $accountService, messageService, $log) {

            $scope.selections = {
                addMore: true
            }

            var initAccount = function () {
                $scope.account = {
                    name: ""
                };
            }

            initAccount();

            $scope.save = function () {

                var onError = function () {
                    messageService.error("Failed to save acocunt");
                };

                var onSuccess = function () {
                    initAccount();

                    if (!$scope.selections.addMore) {
                        $scope.cancel();
                    }

                    messageService.success("Acocunt created");

                };

                $accountService.create($scope.account).then(onSuccess, onError);

            };

            $scope.cancel = function () {
                $scope.$dismiss('cancel');
            };


        });

