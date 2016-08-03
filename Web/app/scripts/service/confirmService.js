angular.module('webApp')
        .factory('ConfirmService', function ($uibModal) {
            var service = {};
            service.open = function (text, onOk) {
                var modalInstance = $uibModal.open({
                    templateUrl: '/views/modal/yesNoModal.html',
                    controller: 'ModalConfirmCtrl',
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

            return service;
        });

