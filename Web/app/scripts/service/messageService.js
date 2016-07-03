'use strict';

//Service that handles generic messages from all controllers
angular.module('webApp')
        .factory('messageService', function ($rootScope, $log) {

            //Subscribe to be able to recieve ERROR messages
            var error = function (scope, callback) {
                var handler = $rootScope.$on('error', callback);
                scope.$on('$destroy', handler);
            };

            //Subscribe to be able to recieve INFO messages
            var info = function (scope, callback) {
                var handler = $rootScope.$on('info', callback);
                scope.$on('$destroy', handler);
            };

            //Subscribe to be able to recieve SUCCESS messages
            var success = function (scope, callback) {
                var handler = $rootScope.$on('success', callback);
                scope.$on('$destroy', handler);
            };

            //Fire INFO message event to INFO subscribers
            var showInfo = function (info) {
                $rootScope.$broadcast('info', info);
            };

            //Fire ERROR message event to ERROR subscribers
            var showError = function (error) {
                $rootScope.$broadcast('error', error);
            };

            //Fire SUCCESS message event to SUCCESS subscribers
            var showSuccess = function (success) {
                $rootScope.$broadcast('success', success);
            };

            return{
                subscribeError: error,
                subscribeInfo: info,
                subscribeSuccess: success,
                info: showInfo,
                error: showError,
                success: showSuccess
            };
        });

