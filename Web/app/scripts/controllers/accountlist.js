'use strict';

/**
 * @ngdoc function
 * @name webApp.controller:AccountlistCtrl
 * @description
 * # AccountlistCtrl
 * Controller of the webApp
 */
angular.module('webApp')
        .controller('AccountlistCtrl', function ($scope, $http) {

            var onError = function (reason) {
                $scope.error = "Failed to get acocunt";
            };

            var onComplete = function (response) {
                $scope.accounts = response.data;
            };

            $http.get("http://localhost:29080/application/rest/account")
                    .then(onComplete, onError);

        });
