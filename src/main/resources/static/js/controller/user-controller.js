'use strict';

(function () {

    var injectParams = ['$scope', '$rootScope', 'abstractService', 'ngTableParams'];

    var userController = function ($scope, $rootScope, abstractService, ngTableParams) {

        $scope.users			= [];

    	$scope.initialize = function() {
            console.log("userController.initialize");

                $scope.table = new ngTableParams({
                    page: 1,
                    count: 20,
                    sorting: { 'id': 'asc' }
                }, {
                    total: 0,
                    getData: function($defer, params) {
                        console.log(params.sorting());
                    	var searchString = '';
                    	var sort = Object.keys(params.sorting())[0];
                    	var dir = params.sorting()[Object.keys(params.sorting())[0]].toUpperCase();

                    	abstractService.get('user', params.page(), params.count(), searchString, sort, dir).then(function (data) {

                        	console.log(data);

                        	params.total(data.totalElements);
                            $defer.resolve(data.content);

                        }, function (error) {
                        	console.log(error);
                        });
                    }
                });
    	};

    	$scope.addUser = function(user) {
            console.log("userController.addUser : " + user);

            abstractService.insert('user', user).then(function (data) {
                console.log(data);
            }, function (error) {
                console.log(error);
            });
    	};

    	// INIT
    	$scope.initialize();

    };

    userController.$inject = injectParams;

    angular.module('MyBankAdvisor').controller('userController', userController);
}());