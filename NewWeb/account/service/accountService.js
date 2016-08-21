angular.module('account').factory('accountService', function (CrudService) {
    return new CrudService("account");
});