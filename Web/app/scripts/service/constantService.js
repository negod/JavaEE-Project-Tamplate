'use strict';

angular.module('webApp')
        .factory('$constantService', function () {
            return{
                baseUrl: "http://localhost:29080/application/rest",
                messageTimeout: 4000
            };
        });


