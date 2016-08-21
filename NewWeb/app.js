angular.module('webapp', ['ui.bootstrap', 'ngRoute', 'ngAnimate', 'common', 'account']);

angular.module('webapp').config(function ($routeProvider) {

    /* Add New Routes Above */
    $routeProvider.otherwise({redirectTo: '/'});

});

angular.module('webapp').run(function ($rootScope) {

    $rootScope.safeApply = function (fn) {
        var phase = $rootScope.$$phase;
        if (phase === '$apply' || phase === '$digest') {
            if (fn && (typeof (fn) === 'function')) {
                fn();
            }
        } else {
            this.$apply(fn);
        }
    };

});
