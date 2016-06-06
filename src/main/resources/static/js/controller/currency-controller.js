'use strict';

(function () {

    var injectParams = ['$scope', '$rootScope', 'currencyService', 'ngTableParams'];

    var currencyController = function ($scope, $rootScope, currencyService, ngTableParams) {

        $scope.currencies			= [];

    	$scope.initialize = function() {
            console.log("currencyController.initialize");

                $scope.table = new ngTableParams({
                    page: 1,
                    count: 20,
                    sorting: { 'code': 'asc' }
                }, {
                    total: 0,
                    getData: function($defer, params) {
                        console.log(params.filter());
                        console.log(params.sorting());

                    	var search = "";
                    	for(var key in params.filter()){
                          search+="&"+key+"="+params.filter()[key];
                        }

                    	var sort = Object.keys(params.sorting())[0];
                    	var dir = params.sorting()[Object.keys(params.sorting())[0]].toUpperCase();

                    	currencyService.get(params.page(), params.count(), sort, dir, search).then(function (data) {

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
            console.log("synchController.synch");
            currencyService.synch().then(function (data) {
                console.log(data);
            }, function (error) {
                console.log(error);
            });
    	};

    	// INIT
    	$scope.initialize();

    };

    currencyController.$inject = injectParams;

    angular.module('MyBankAdvisor').controller('currencyController', currencyController);
}());