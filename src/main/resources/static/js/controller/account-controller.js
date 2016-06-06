'use strict';

(function () {

    var injectParams = ['$scope', '$rootScope', 'accountService', 'currencyService', 'ngTableParams'];

    var accountController = function ($scope, $rootScope, accountService, currencyService, ngTableParams) {

        $scope.accounts			= [];
        $scope.currencies			= [];

    	$scope.initialize = function() {
            console.log("accountController.initialize");

            currencyService.get(1, 1000, 'code', 'ASC').then(function (data) {
                    console.log(data);
                    $scope.currencies = data.content;

                }, function (error) {
                    console.log(error);
            });

            $scope.table = new ngTableParams({
                    page: 1,
                    count: 20,
                    sorting: { 'id': 'asc' }
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

                    	accountService.get(params.page(), params.count(), sort, dir, search).then(function (data) {

                        	console.log(data);

                        	params.total(data.totalElements);
                            $defer.resolve(data.content);

                        }, function (error) {
                        	console.log(error);
                        });
                    }
                });
    	};

    	$scope.add = function(account) {

    	    if ($scope.form.$valid) {

            	accountService.add(account).then(function (data) {

            		$scope.table.reload();

            	}, function (error) {
                      console.log(error);
                });
    	    }
    	};


    	// INIT
    	$scope.initialize();

    };

    accountController.$inject = injectParams;

    angular.module('MyBankAdvisor').controller('accountController', accountController);
}());