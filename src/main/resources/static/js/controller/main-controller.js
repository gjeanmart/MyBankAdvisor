'use strict';

(function () {

    var injectParams = ['$scope', '$rootScope', 'authorization'];

    var mainController = function ($scope, $rootScope, authorization) {

        $scope.session			= {};

    	$scope.initialize = function() {
            console.log("mainController.initialize");

            $scope.session = authorization.profile;
    	};

    	// INIT
    	$scope.initialize();

    };

    mainController.$inject = injectParams;

    angular.module('MyBankAdvisor').controller('mainController', mainController);
}());