console.log("Loading <SectorsRouter.ng.js>...");

angular.module("avelon").config(function($urlRouterProvider, $stateProvider, $locationProvider) {

	console.log("Create state <bubble-markets>");
	$stateProvider.state("bubble-markets", {
		url: "/bubble-markets",
	        templateUrl: "client/technicalanalysis/BubbleMarketsView.ng.html",
		controller: "BubbleMarketsController"
	});
});
