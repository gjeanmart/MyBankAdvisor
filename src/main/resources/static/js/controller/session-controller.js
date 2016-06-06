'use strict';

(function () {

    var injectParams = ['$scope', '$rootScope', 'authorization'];

    var sessionController = function ($scope, $rootScope, authorization) {

        $scope.session			= {};

    	$scope.initialize = function() {
            console.log("sessionController.initialize");

            $scope.session = authorization.profile;
            $scope.session.roles = [];

            if(authorization.hasRealmRole('MBA-USER')) $scope.session.roles.push('MBA-USER');
            if(authorization.hasRealmRole('MBA-ADMIN')) $scope.session.roles.push('MBA-ADMIN');

            console.log($scope.session);

    	};

    	$scope.logout = function() {
            console.log("sessionController.logout");

        	authorization.logout();
    	};

    	// INIT
    	$scope.initialize();

    };

    sessionController.$inject = injectParams;

    angular.module('MyBankAdvisor').controller('sessionController', sessionController);
}());