'use strict';

/**
 * @ngdoc function
 * @name webApp.controller:AccountCtrl
 * @description
 * # AccountCtrl
 * Controller of the webApp
 */
angular.module('webApp')
        .controller('AccountCtrl', function ($scope, $http) {

            var onError = function (reason) {
                $scope.error = "Failed to get acocunt";
            };

            var onComplete = function (response) {
                $scope.account = response.data;
            };

            $http.get("http://localhost:29080/application/rest/account/71d42f46-cd2a-419d-bfcf-27b21ded1487")
                    .then(onComplete, onError);

        });
