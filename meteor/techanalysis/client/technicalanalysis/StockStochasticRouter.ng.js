console.log("Loading <StockStochasticRouter.ng.js>...");

angular.module("avelon").config(function($urlRouterProvider, $stateProvider, $locationProvider) {

	console.log("Create state <stock-stochastic>");
	$stateProvider.state("stock-stochastic", {
		url: "/stock-stochastic/:id/:days",
	        templateUrl: "client/technicalanalysis/StockStochasticView.ng.html",
		controller: "StockStochasticController"
	});
});
