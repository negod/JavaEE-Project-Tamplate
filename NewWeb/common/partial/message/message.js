angular.module('common').controller('MessageCtrl', function ($scope, $timeout, messageService, constantService) {

    function clearMessage() {
        $scope.error = undefined;
        $scope.info = undefined;
        $scope.success = undefined;
    }

    messageService.subscribeError($scope, function handleError(event, error) {
        $scope.error = error;
        $timeout(clearMessage, constantService.messageTimeout);
    });

    messageService.subscribeInfo($scope, function handleInfo(event, info) {
        $scope.info = info;
        $timeout(clearMessage, constantService.messageTimeout);
    });

    messageService.subscribeSuccess($scope, function handleSuccess(event, success) {
        $scope.success = success;
        $timeout(clearMessage, constantService.messageTimeout);
    });

});