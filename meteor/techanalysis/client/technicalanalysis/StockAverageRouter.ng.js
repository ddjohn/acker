console.log("Loading <StockAverageRouter.ng.js>...");

angular.module("avelon").config(function($urlRouterProvider, $stateProvider, $locationProvider) {

	console.log("Create state <bubble-average>");
	$stateProvider.state("stock-average", {
		url: "/stock-average/:id/:days",
	        templateUrl: "client/technicalanalysis/StockAverageView.ng.html",
		controller: "StockAverageController"
	});
});
