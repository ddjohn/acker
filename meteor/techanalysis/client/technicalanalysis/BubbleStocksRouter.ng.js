console.log("Loading <SectorsRouter.ng.js>...");

angular.module("avelon").config(function($urlRouterProvider, $stateProvider, $locationProvider) {

	console.log("Create state <bubble-stocks>");
	$stateProvider.state("bubble-stocks", {
		url: "/bubble-stocks",
	        templateUrl: "client/technicalanalysis/BubbleStocksView.ng.html",
		controller: "BubbleStocksController"
	});
});
