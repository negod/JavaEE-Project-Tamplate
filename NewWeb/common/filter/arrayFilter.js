angular.module('common').filter('arrayFilter', function () {
    return function (value) {
        if (!angular.isArray(value)) {
            return '';
        } else {
            return value.join(', ');
        }
    };
});