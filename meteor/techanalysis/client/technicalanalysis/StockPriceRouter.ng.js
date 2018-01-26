console.log("Loading <StockPriceRouter.ng.js>...");

angular.module("avelon").config(function($urlRouterProvider, $stateProvider, $locationProvider) {

	console.log("Create state <stock-price>");
	$stateProvider.state("stock-price", {
		url: "/stock-price/:id/:days",
	        templateUrl: "client/technicalanalysis/StockPriceView.ng.html",
		controller: "StockPriceController"
	});

});
