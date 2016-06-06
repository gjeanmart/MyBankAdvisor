(function () {

    var injectParams = ['$http'];

    var currencyFactory = function ($http) {
        console.log("currencyFactory");

        var serviceBase = 'http://localhost:8081/api/v1/currency/',
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

        factory.synch = function () {
             return $http({
                 method  : 'POST',
                 url     : serviceBase + 'sync',
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

    currencyFactory.$inject = injectParams;

    angular.module('MyBankAdvisor').factory('currencyService', currencyFactory);

}());