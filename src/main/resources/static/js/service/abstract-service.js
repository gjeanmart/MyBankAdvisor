(function () {

    var injectParams = ['$http'];

    var abstractFactory = function ($http) {
        var factory       = {};
        var serviceBase   = 'http://localhost:8081/api/v1/';

        factory.get = function (clazz, page, size, sort, dir, search) {
        	if(search === undefined||search === null)
        		search = '';

             return $http({
                 method  : 'GET',
                 url     : serviceBase + clazz + '/' + '?page=' + page + '&size=' + size + '&sort=' + sort + '&dir=' + dir + search,
                 data    : '',
                 headers : { 'Content-Type': 'application/json' }
             }).then(
                 function(results) {
                     return results.data;
                 }
             );
        };

        factory.add = function (clazz, obj) {
            return $http({
                method  : 'POST',
                url     : serviceBase + clazz + '/',
                data    : obj,
                headers : { 'Content-Type': 'application/json' }
            }).then(
                function(results) {
            	    obj.id = results.data.id;
                    return results.data;
                }
            );

        };

        factory.edit = function (clazz, obj) {
            return $http({
                method  : 'PUT',
                url     : serviceBase + clazz + "/" + obj.id,
                data    : obj,
                headers : { 'Content-Type': 'application/json' }
            }).then(
                function(results) {
            	    obj.id = results.data.id;
                    return results.data;
                }
            );

        };

        factory.delete = function (clazz, obj) {
            return $http({
                method  : 'DELETE',
                url     : serviceBase + clazz + "/" + obj.id,
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

    abstractFactory.$inject = injectParams;

    angular.module('MyBankAdvisor').factory('abstractService', abstractFactory);

}());