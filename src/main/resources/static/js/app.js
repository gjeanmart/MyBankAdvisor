	/**
	 * MODULE
	 */
	var app = angular.module('MyBankAdvisor',
			['ngRoute',
			 'ngTable',
			 'ngResource',
			 'ng.httpLoader',
			 'isteven-multi-select'
			]);


	/**
	 * CONFIG
	 */
	// configure our routes
	app.config(
	    function($routeProvider) {
            var viewBase = 'views/';

            $routeProvider
                .when('/', {
                    templateUrl : viewBase + 'home.html',
                    controller  : 'mainController'
                })
                .when('/account', {
                    templateUrl : viewBase + 'accounts.html',
                    controller  : 'accountController'
                })
                .when('/bank', {
                    templateUrl : viewBase + 'banks.html',
                    controller  : 'bankController'
                })
                .when('/user/user', {
                    templateUrl : viewBase + 'user.html',
                    controller  : 'userController'
                })
                .when('/user/user-add', {
                    templateUrl : viewBase + 'user-add.html',
                    controller  : 'userController'
                })
                .when('/currencies', {
                    templateUrl : viewBase + 'currencies.html',
                    controller  : 'currencyController'
                })
                .when('/countries', {
                    templateUrl : viewBase + 'countries.html',
                    controller  : 'countryController'
                })
                .otherwise({
                    redirectTo: '/'
                });
	    }
	).config(['$httpProvider',
	    function($httpProvider) {
            var isExpired = window._keycloak.isTokenExpired();
            var token = window._keycloak.token;

            if (isExpired) {
                window._keycloak.updateToken(5)
                .success(function() {
                    $httpProvider.defaults.headers.common['Authorization'] = 'BEARER ' + token;
                })
                .error(function() {
                    console.error('Failed to refresh token');
                });
            }

            $httpProvider.defaults.headers.common['Authorization'] = 'BEARER ' + token;
        }

    // HTTP Loader
    ]).config(['httpMethodInterceptorProvider',
        function (httpMethodInterceptorProvider) {
            httpMethodInterceptorProvider.whitelistDomain('localhost');
            httpMethodInterceptorProvider.whitelistLocalRequests();
        }
    ]);

    // application startup hook
    app.run(['$rootScope', '$location', '$http', '$route',
             function ($rootScope, $location, $http, usersService, $route) {

    }]);


    // provide keycloak as factory to make it injectable
    angular.module('MyBankAdvisor').factory('authorization', function ($window) {
    	return $window._keycloak;
    });

    // on every request, authenticate user first
    angular.element(document).ready(() => {
    	window._keycloak = Keycloak('keycloak/keycloak.json');

    	window._keycloak.init({
    		onLoad: 'login-required'
    	})
    	.success((authenticated) => {
    		if(authenticated) {
    			window._keycloak.loadUserProfile().success(function(profile){
    				angular.bootstrap(document, ['MyBankAdvisor']); // manually bootstrap Angular
    			});
    		}
    		else {
    			window.location.reload();
    		}
    	})
    	.error(function () {
    		window.location.reload();
    	});
    });