'use strict';

(function () {

    var injectParams = ['$scope', '$rootScope', '$modal', 'abstractService', 'ngTableParams'];

    var accountController = function ($scope, $rootScope, $modal, abstractService, ngTableParams) {
        var self = this;

        $scope.account      = {};
        $scope.currentAccount      = {};

        $scope.accounts		= [];
        $scope.currencies	= [];
        $scope.banks	= [];

    	$scope.initialize = function() {
            console.log("accountController.initialize");

            abstractService.get('bank', 1, 1000, 'name', 'ASC').then(function (data) {
                    console.log(data);
                    $scope.banks = data.content;

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

                    	abstractService.get('account', params.page(), params.count(), sort, dir, search).then(function (data) {

                        	console.log(data);

                        	params.total(data.totalElements);
                            $defer.resolve(data.content);

                        }, function (error) {
                        	console.log(error);
                        });
                    }
                });
    	};

        $scope.open = function (size) {
            var modalInstance = $modal.open({
                size: size,
                animation: false,
                backdrop: 'static',
                templateUrl: 'account-form.html',
                controller: 'accountController',
                resolve: {
                    account: function () {
                        return $scope.account;
                    }
                }
            });
            modalInstance.result.then(function (response) {
                debugger;
                $scope.currentAccount = response;
                $state.go('customer.detail', { 'customerId': response.CustomerId });
            }, function () {
                $log.info('Modal dismissed at: ' + new Date());
            });
        };

    	$scope.add = function(account) {
    	    if ($scope.form.$valid) {
            	abstractService.add('account', account).then(function (data) {
            	    $scope.reset();
            	    $scope.table.reload();
            	}, function (error) {
                      console.log(error);
                });
    	    } else {
                console.log("validation error");
    	    }
    	};

        $scope.edit = function(account) {
    	    if ($scope.form.$valid) {
                abstractService.edit('account', account).then(function (data) {
                    console.log(data);
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
            abstractService.delete('account', row).then(function (data) {
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



        $scope.reset = function() {
            $scope.account = {};
        };

    	// INIT
    	$scope.initialize();

    };

    accountController.$inject = injectParams;

    angular.module('MyBankAdvisor').controller('accountController', accountController);
}());