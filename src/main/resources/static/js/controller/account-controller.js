'use strict';

(function () {

    var injectParams = ['$scope', '$rootScope', 'accountService', 'currencyService', 'ngTableParams'];

    var accountController = function ($scope, $rootScope, accountService, currencyService, ngTableParams) {
        var self = this;

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

        $scope.cancel = function(row, rowForm) {
            var originalRow = $scope.resetRow(row, rowForm);
            angular.extend(row, originalRow);
        };

        $scope.del = function(row) {
            accountService.delete(row).then(function (data) {
                console.log(data);
            	$scope.table.reload();
             }, function (error) {
                console.log(error);
            });
        };

        $scope.resetRow = function(row, rowForm){
            row.isEditing = false;
            rowForm.$setPristine();
            self.tableTracker.untrack(row);

            return _.findWhere(originalData, function(r){
                return r.id === row.id;
            });
        };

        $scope.save = function(row, rowForm) {
            accountService.edit(row).then(function (data) {
                console.log(data);
            	$scope.table.reload();
             }, function (error) {
                console.log(error);
            });

        };

    	// INIT
    	$scope.initialize();

    };

    accountController.$inject = injectParams;

    angular.module('MyBankAdvisor').controller('accountController', accountController);
}());