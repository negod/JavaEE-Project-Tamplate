angular.module('common').factory('constantService', function () {

    var constantService = {
        baseUrl: "http://localhost:29080/application/rest",
        messageTimeout: 4000,
        searchQuery: {
            searchFields: [
            ],
            globalSearchWord: null,
            pagination: {
                listSize: 10,
                page: 0
            }
        }
    };

    return constantService;
});