angular.module('account').controller('AccountsCtrl', function ($scope, accountService, constantService, $uibModal, $log) {

    $scope.searchQuery = angular.copy(constantService.searchQuery);
    var initListSize = $scope.searchQuery.pagination.listSize;
    $scope.searchQuery.searchFields = accountService.searchFields();
    $scope.accounts = accountService.list($scope.searchQuery);


    $scope.getMore = function () {
        var listSize = $scope.searchQuery.pagination.listSize;
        var page = $scope.searchQuery.pagination.page + 1;
        if ((listSize * page) === $scope.accounts.length) {
            accountService.list($scope.searchQuery).$promise.then(function (response) {
                $log.info(response);
                $scope.accounts = $scope.accounts.concat(response);
            });
            $scope.searchQuery.pagination.page += 1;
        }
    };

    $scope.search = function () {
        $scope.searchQuery.pagination.listSize = initListSize;
        $scope.searchQuery.pagination.page = 0;
        accountService.list($scope.searchQuery).$promise.then(function (response) {
            $scope.accounts = response;
        });
    };

    $scope.deleteAccount = function (account) {
        account.$delete(function (response) {
            $scope.accounts.splice($scope.accounts.indexOf(account), 1);
        });
    };


    $scope.showModal = function (size, account) {
        $uibModal.open({
            animation: $scope.animationsEnabled,
            templateUrl: 'account/partial/account/account.html',
            controller: 'AccountCtrl',
            backdrop: 'static',
            size: size,
            resolve: {
                account: function () {
                    return account;
                },
                accounts: function () {
                    return $scope.accounts;
                }
            }
        }).result.then(function (result) {
            $log.info("Result " + result);
        });
    };

});