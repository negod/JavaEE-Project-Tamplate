angular.module('common').directive('confirm', function (confirmService) {
    return {
        restrict: 'A',
        scope: {
            eventHandler: '&ngClick'
        },
        link: function (scope, element, attrs) {
            element.unbind("click");
            element.bind("click", function (e) {
                confirmService.open(attrs.confirm, scope.eventHandler);
            });
        }
    };
});