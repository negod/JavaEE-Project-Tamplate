'use strict';

describe('Service: CrudService', function () {

  // load the service's module
  beforeEach(module('webApp'));

  // instantiate service
  var CrudService;
  beforeEach(inject(function (_CrudService_) {
    CrudService = _CrudService_;
  }));

  it('should do something', function () {
    expect(!!CrudService).toBe(true);
  });

});
