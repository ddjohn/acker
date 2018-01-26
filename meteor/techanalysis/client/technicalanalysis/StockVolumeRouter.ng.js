console.log("Loading <StockVolumeRouter.ng.js>...");

angular.module("avelon").config(function($urlRouterProvider, $stateProvider, $locationProvider) {

	console.log("Create state <stock-volume>");
	$stateProvider.state("stock-volume", {
		url: "/stock-volume/:id/:days",
	        templateUrl: "client/technicalanalysis/StockVolumeView.ng.html",
		controller: "StockVolumeController"
	});
});
