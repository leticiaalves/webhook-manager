var app = angular.module('app', [ 'ngRoute', 'ngResource' ]);

app.config(function($routeProvider) {
	
	$routeProvider.when('/home', {
		templateUrl : '/views/home.html'
			
	}).when('/dashboard', {
		templateUrl : '/views/dashboard.html',
		controller : 'dashboardController'
			
	}).when('/about', {
		templateUrl : '/views/about.html',
		controller : 'aboutController'
			
	}).otherwise({
		redirectTo : '/home'
	});
});