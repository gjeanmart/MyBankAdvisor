'use strict';

(function () {

    var injectParams = ['$scope', '$rootScope', 'countryService', 'ngTableParams'];

    var countryController = function ($scope, $rootScope, countryService, ngTableParams) {

        $scope.countries			= [];
        $scope.query;

        $scope.search = function() {
            console.log("$scope.query="+$scope.query);
            if($scope.query.length >= 3 || $scope.query.length == 0) {
                $scope.table.reload();
            }
        };

    	$scope.initialize = function() {
            console.log("countryController.initialize");

                $scope.table = new ngTableParams({
                    page: 1,
                    count: 20,
                    sorting: { 'alpha3Code': 'asc' }
                }, {
                    total: 0,
                    getData: function($defer, params) {
                        console.log(params.sorting());
                    	var searchString = $scope.query;
                    	var sort = Object.keys(params.sorting())[0];
                    	var dir = params.sorting()[Object.keys(params.sorting())[0]].toUpperCase();

                    	countryService.get(params.page(), params.count(), sort, dir, searchString).then(function (data) {

                        	console.log(data);

                        	params.total(data.totalElements);
                            $defer.resolve(data.content);

                        }, function (error) {
                        	console.log(error);
                        });
                    }
                });
    	};

    	$scope.synch = function() {
            console.log("countryController.synch");
            countryService.synch().then(function (data) {
                console.log(data);
            }, function (error) {
                console.log(error);
            });
    	};

    	// INIT
    	$scope.initialize();

    };

    countryController.$inject = injectParams;

    angular.module('MyBankAdvisor').controller('countryController', countryController);
}());