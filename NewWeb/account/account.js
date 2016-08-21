angular.module('account', ['ui.bootstrap', 'ngRoute', 'ngAnimate']);

angular.module('account', ['common']).config(function ($routeProvider) {

    $routeProvider.when('/accounts', {templateUrl: 'account/partial/accounts/accounts.html'});
    $routeProvider.when('/account',{templateUrl: 'account/partial/account/account.html'});
    /* Add New Routes Above */

});

