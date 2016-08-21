angular.module('account').controller('AccountCtrl', function ($scope, accountService, $log, account, accounts) {


    $scope.selections = {
        addMore: true,
        edit: account !== undefined ? true : false,
        text: account !== undefined ? "Edit Account" : "Create new Account"
    };

    $scope.account = account;

    $scope.save = function () {

        if ($scope.selections.edit === true) {
            accountService.update($scope.account).$promise.then(function (response) {
                //messageService.success("Acocunt updated");
            });
        } else {
            accountService.save($scope.account).$promise.then(function (response) {
                accounts.push(response);
                //messageService.success("Acocunt created");
            });
        }

        if ($scope.selections.addMore === false || $scope.selections.edit === true) {
            $scope.cancel();
        }

    };

    $scope.cancel = function () {
        $scope.$dismiss('cancel');
    };

});