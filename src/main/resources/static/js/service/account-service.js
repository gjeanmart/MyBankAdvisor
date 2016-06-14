(function () {

    var injectParams = ['$http'];

    var accountFactory = function ($http) {
        console.log("accountFactory");

        var serviceBase = 'http://localhost:8081/api/v1/account/',
            factory = {};

        factory.get = function (page, size, sort, dir, search) {
        	if(search === undefined||search === null)
        		search = '';

             return $http({
                 method  : 'GET',
                 url     : serviceBase + '?page=' + page + '&size=' + size + '&sort=' + sort + '&dir=' + dir + search,
                 data    : '',
                 headers : { 'Content-Type': 'application/json' }
             }).then(
                 function(results) {
                     return results.data;
                 }
             );
        };

        factory.add = function (obj) {
            return $http({
                method  : 'POST',
                url     : serviceBase,
                data    : obj,
                headers : { 'Content-Type': 'application/json' }
            }).then(
                function(results) {
            	    obj.id = results.data.id;
                    return results.data;
                }
            );

        };

        factory.edit = function (obj) {
            return $http({
                method  : 'PUT',
                url     : serviceBase + obj.id,
                data    : obj,
                headers : { 'Content-Type': 'application/json' }
            }).then(
                function(results) {
            	    obj.id = results.data.id;
                    return results.data;
                }
            );

        };

        factory.delete = function (obj) {
            return $http({
                method  : 'DELETE',
                url     : serviceBase + obj.id,
                data    : '',
                headers : { 'Content-Type': 'application/json' }
            }).then(
                function(results) {
            	    obj.id = results.data.id;
                    return results.data;
                }
            );

        };


        return factory;
    };

    accountFactory.$inject = injectParams;

    angular.module('MyBankAdvisor').factory('accountService', accountFactory);

}());