console.log("Loading <StockCandlestickRouter.ng.js>...");

angular.module("avelon").config(function($urlRouterProvider, $stateProvider, $locationProvider) {

	console.log("Create state <stock-candlestick>");
	$stateProvider.state("stock-candlestick", {
		url: "/stock-candlestick/:id/:days",
	        templateUrl: "client/technicalanalysis/StockCandlestickView.ng.html",
		controller: "StockCandlestickController"
	});
});
