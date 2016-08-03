'use strict';

/**
 * @ngdoc overview
 * @name webApp
 * @description
 * # webApp
 *
 * Main module of the application.
 */
angular
        .module('webApp', [
            'ngAnimate',
            'ngCookies',
            'ngResource',
            'ngRoute',
            'ngSanitize',
            'ngTouch',
            'ui.bootstrap'            
        ])
        .config(function ($routeProvider) {
            $routeProvider
                    .when('/about', {
                        templateUrl: 'views/about.html',
                        controller: 'AboutCtrl',
                        controllerAs: 'about'
                    })
                    .when('/account', {
                        templateUrl: 'views/account.html',
                        controller: 'AccountListCtrl',
                        controllerAs: 'account'
                    })
                    .otherwise({
                        redirectTo: '/'
                    });
        });
