(function () {

    var injectParams = ['$http'];

    var userFactory = function ($http) {
        console.log("userFactory");

        var serviceBase = 'http://localhost:8081/api/v1/user/',
            factory = {};

        factory.insert = function (user) {
            return $http({
                method  : 'POST',
                url     : serviceBase,
                data    : user,
                headers : { 'Content-Type': 'application/json' }
            }).then(
                function(results) {
            	    user.id = results.data.id;
                    return results.data;
                }
            );

        };

        factory.update = function (user) {
            return $http({
                method  : 'PUT',
                url     : serviceBase + id,
                data    : user,
                headers : { 'Content-Type': 'application/json' }
            }).then(
                function(results) {
                    return results.data;
                }
            );
        };

        factory.delete = function (id) {
            return $http({
                method  : 'DELETE',
                url     :serviceBase + id,
                data    : '',
                headers : { 'Content-Type': 'application/json' }
            }).then(
                function(results) {
                    return results.data;
                }
            );
        };

        factory.get = function (id) {
            return $http({
                method  : 'GET',
                url     :serviceBase + id,
                data    : '',
                headers : { 'Content-Type': 'application/json' }
            }).then(
                function(results) {
                    return results.data;
                }
            );
        };

        factory.get = function (page, size, search, sort, dir) {
        	if(search === undefined||search === null)
        		search = '';

             return $http({
                 method  : 'GET',
                 url     : serviceBase + '?page=' + page + '&size=' + size + '&sort=' + sort + '&dir=' + dir,
                 data    : '',
                 headers : { 'Content-Type': 'application/json' }
             }).then(
                 function(results) {
                     return results.data;
                 }
             );
        };

        return factory;
    };

    userFactory.$inject = injectParams;

    angular.module('MyBankAdvisor').factory('userService', userFactory);

}());