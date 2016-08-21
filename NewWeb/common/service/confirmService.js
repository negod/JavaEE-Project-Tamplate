angular.module('common').factory('confirmService', function ($uibModal) {

    var confirmService = {};
    confirmService.open = function (text, onOk) {
        var modalInstance = $uibModal.open({
            templateUrl: 'common/partial/confirm/confirm.html',
            controller: 'ConfirmCtrl',
            resolve: {
                text: function () {
                    return text;
                }
            }
        });

        modalInstance.result.then(function (selectedItem) {
            onOk();
        }, function () {
        });
    };

    return confirmService;
});