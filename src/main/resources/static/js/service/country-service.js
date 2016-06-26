(function () {

    var injectParams = ['$http'];

    var countryFactory = function ($http) {
        console.log("countryFactory");

        var serviceBase = 'http://localhost:8081/api/v1/country/',
            factory = {};

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

    countryFactory.$inject = injectParams;

    angular.module('MyBankAdvisor').factory('countryService', countryFactory);

}());