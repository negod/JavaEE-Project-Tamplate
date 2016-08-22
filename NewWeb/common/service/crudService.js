angular.module('common').factory('CrudService', function (constantService, $resource) {
    var CrudService = function (serviceName) {
        var serviceUrl = constantService.baseUrl + '/' + serviceName;
        return $resource(serviceUrl + '/:id', {id: '@id'},
                {
                    list: {
                        method: 'POST',
                        isArray: true,
                        url: serviceUrl + '/list'},
                    create: {
                        method: 'POST',
                        isArray: false,
                        url: serviceUrl
                    },
                    searchFields: {
                        method: 'GET',
                        isArray: true,
                        url: serviceUrl + '/search/fields'},
                    update: {
                        method: 'PUT',
                    }
                });
    };
    return CrudService;
});
