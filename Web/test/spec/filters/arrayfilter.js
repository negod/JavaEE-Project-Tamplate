'use strict';

describe('Filter: ArrayFilter', function () {

  // load the filter's module
  beforeEach(module('webApp'));

  // initialize a new instance of the filter before each test
  var ArrayFilter;
  beforeEach(inject(function ($filter) {
    ArrayFilter = $filter('ArrayFilter');
  }));

  it('should return the input prefixed with "ArrayFilter filter:"', function () {
    var text = 'angularjs';
    expect(ArrayFilter(text)).toBe('ArrayFilter filter: ' + text);
  });

});
