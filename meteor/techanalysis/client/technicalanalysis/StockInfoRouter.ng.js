console.log("Loading <StockInfoRouter.ng.js>...");

angular.module("avelon").config(function($urlRouterProvider, $stateProvider, $locationProvider) {

	console.log("Create state <stock-info>");
	$stateProvider.state("stock-info", {
		url: "/stock-info/:id/:days",
	        templateUrl: "client/technicalanalysis/StockInfoView.ng.html",
		controller: "StockInfoController"
	});
});
