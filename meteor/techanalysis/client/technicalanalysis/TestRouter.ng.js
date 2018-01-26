console.log("Loading <TestRouter.ng.js>...");

angular.module("avelon").config(function($urlRouterProvider, $stateProvider, $locationProvider) {

	console.log("Create state <donchian>");
	$stateProvider.state("donchian", {
		url: "/donchian/:id/:days",
	        templateUrl: "client/technicalanalysis/TestGraph.ng.html",
		controller: "TestCtrl9"
	});
});
