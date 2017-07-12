'use strict';

/**
 * @ngdoc function
 * @name webApp.controller:AccountlistCtrl
 * @description
 * # AccountlistCtrl 
 * Controller of the webApp
 */
angular.module('webApp')
        .controller('MessageCtrl', function ($scope, messageService, $timeout, ConstantService) {

            function clearMessage() {
                $scope.error = undefined;
                $scope.info = undefined;
                $scope.success = undefined;
            }

            messageService.subscribeError($scope, function handleError(event, error) {
                $scope.error = error;
                $timeout(clearMessage, ConstantService.messageTimeout);
            });

            messageService.subscribeInfo($scope, function handleInfo(event, info) {
                $scope.info = info;
                $timeout(clearMessage, ConstantService.messageTimeout);
            });

            messageService.subscribeSuccess($scope, function handleSuccess(event, success) {
                $scope.success = success;
                $timeout(clearMessage, ConstantService.messageTimeout);
            });

        });