'use strict';

describe('Controller: AccountlistCtrl', function () {

  // load the controller's module
  beforeEach(module('webApp'));

  var AccountlistCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    AccountlistCtrl = $controller('AccountlistCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(AccountlistCtrl.awesomeThings.length).toBe(3);
  });
});
