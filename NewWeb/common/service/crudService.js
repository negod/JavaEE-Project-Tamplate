angular.module('common').factory('CrudService', function (constantService, $resource) {
    var CrudService = function (serviceName) {
        var serviceUrl = constantService.baseUrl + '/' + serviceName;
        return $resource(serviceUrl + '/:id', {id: '@id'},
                {
                    list: {
                        method: 'POST',
                        isArray: true,
                        params: {},
                        url: serviceUrl + '/list'},
                    searchFields: {
                        method: 'GET',
                        isArray: true,
                        params: {},
                        url: serviceUrl + '/search/fields'},
                    update: {
                        method: 'PUT',
                        params: {},
                    }
                });
    };
    return CrudService;
});
