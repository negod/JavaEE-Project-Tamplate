angular.module('webapp', ['ui.bootstrap','ngRoute','ngAnimate']);

angular.module('webapp').config(function($routeProvider) {

    $routeProvider.when('/account',{templateUrl: 'partial/account/account.html'});
    /* Add New Routes Above */
    $routeProvider.otherwise({redirectTo:'/home'});

});

angular.module('webapp').run(function($rootScope) {

    $rootScope.safeApply = function(fn) {
        var phase = $rootScope.$$phase;
        if (phase === '$apply' || phase === '$digest') {
            if (fn && (typeof(fn) === 'function')) {
                fn();
            }
        } else {
            this.$apply(fn);
        }
    };

});
