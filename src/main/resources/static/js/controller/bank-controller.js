'use strict';

(function () {

    var injectParams = ['$scope', '$rootScope', 'abstractService', 'ngTableParams'];

    var bankController = function ($scope, $rootScope, abstractService, ngTableParams) {
        var self = this;

        $scope.bank           = {};
        $scope.banks		  = [];
        $scope.countries	  = [];
        $scope.currencies	  = [];

    	$scope.initialize = function() {
            console.log("bankController.initialize");

            abstractService.get('country', 1, 1000, 'name', 'ASC').then(function (data) {
                    console.log(data);
                    $scope.countries = data.content;

                }, function (error) {
                    console.log(error);
            });

            abstractService.get('currency', 1, 1000, 'code', 'ASC').then(function (data) {
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

                    	abstractService.get('bank', params.page(), params.count(), sort, dir, search).then(function (data) {

                        	console.log(data);

                        	params.total(data.totalElements);
                            $defer.resolve(data.content);

                        }, function (error) {
                        	console.log(error);
                        });
                    }
                });
    	};

    	$scope.add = function(bank) {
    	    if ($scope.form.$valid) {
            	abstractService.add('bank', bank).then(function (data) {
            	    $scope.reset();
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
            abstractService.delete('bank', row).then(function (data) {
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
            abstractService.edit('bank', row).then(function (data) {
                console.log(data);
            	$scope.table.reload();
             }, function (error) {
                console.log(error);
            });

        };

        $scope.reset = function() {
            $scope.bank = {};
        };

    	// INIT
    	$scope.initialize();

    };

    bankController.$inject = injectParams;

    angular.module('MyBankAdvisor').controller('bankController', bankController);
}());