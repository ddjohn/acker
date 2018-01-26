console.log("Loading <StocksRoutes.ng.js>...");

angular.module("avelon").config(function($urlRouterProvider, $stateProvider, $locationProvider) {

	console.log("Create state <stocks>");
	$stateProvider.state("stocks", {
		url: "/stocks",
		templateUrl: "client/stocks/StocksListView.ng.html",
		controller: "StocksListController"
	})
	.state("stockDetails", {
		url: "/stocks/:id",
		templateUrl: "client/stocks/StockDetailsView.ng.html",
		controller: "StockDetailsController"
	});
});
