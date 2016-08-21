angular.module('common').controller('ConfirmCtrl', function ($uibModalInstance, $scope, text) {

    $scope.text = text;

    $scope.ok = function () {
        $uibModalInstance.close(true);
    };

    $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };

});