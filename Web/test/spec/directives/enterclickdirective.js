'use strict';

describe('Directive: EnterClickDirective', function () {

  // load the directive's module
  beforeEach(module('webApp'));

  var element,
    scope;

  beforeEach(inject(function ($rootScope) {
    scope = $rootScope.$new();
  }));

  it('should make hidden element visible', inject(function ($compile) {
    element = angular.element('<-enter-click-directive></-enter-click-directive>');
    element = $compile(element)(scope);
    expect(element.text()).toBe('this is the EnterClickDirective directive');
  }));
});
