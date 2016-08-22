angular.module('account').controller('AccountCtrl', function ($scope, accountService, $log, account, accounts) {


    $scope.selections = {
        addMore: true,
        edit: account !== undefined ? true : false,
        text: account !== undefined ? "Edit Account" : "Create new Account"
    };

    $scope.oldAccount = angular.copy(account, $scope.account);
    $scope.account = account;

    $scope.save = function () {
        if ($scope.selections.edit === true) {
            $scope.account.$update(function (response) {
                $scope.$dismiss('cancel');
            });
        } else {
            accountService.save($scope.account, function (response) {
                accounts.push(response);
                $scope.account = {};
            });
        }

        if ($scope.selections.addMore === false || $scope.selections.edit === true) {
            $scope.$dismiss('cancel');
        }

    };

    $scope.cancel = function () {
        if ($scope.selections.edit === true) {
            $scope.account = angular.copy($scope.oldAccount, $scope.account);
        }
        $scope.$dismiss('cancel');
    };

});