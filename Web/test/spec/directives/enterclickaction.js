'use strict';

describe('Directive: EnterClickAction', function () {

  // load the directive's module
  beforeEach(module('webApp'));

  var element,
    scope;

  beforeEach(inject(function ($rootScope) {
    scope = $rootScope.$new();
  }));

  it('should make hidden element visible', inject(function ($compile) {
    element = angular.element('<-enter-click-action></-enter-click-action>');
    element = $compile(element)(scope);
    expect(element.text()).toBe('this is the EnterClickAction directive');
  }));
});
