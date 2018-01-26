console.log("Loading <StockMarketsRouter.ng.js>...");

angular.module("avelon").config(function($urlRouterProvider, $stateProvider, $locationProvider) {

	console.log("Create state <stock-markets>");
	$stateProvider.state("stock-markets", {
		url: "/stock-markets",
	        templateUrl: "client/technicalanalysis/StockMarketsView.ng.html",
		controller: "StockMarketsController"
	});
});
