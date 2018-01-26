console.log("Loading <StockRsiRouter.ng.js>...");

angular.module("avelon").config(function($urlRouterProvider, $stateProvider, $locationProvider) {

	console.log("Create state <stock-rsi>");
	$stateProvider.state("stock-rsi", {
		url: "/stock-rsi/:id/:days",
	        templateUrl: "client/technicalanalysis/StockRsiView.ng.html",
		controller: "StockRsiController"
	});
});
