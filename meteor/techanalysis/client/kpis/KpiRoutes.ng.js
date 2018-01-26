console.log("Loading <KpiRoutes.js>...");

angular.module("avelon").config(function($urlRouterProvider, $stateProvider, $locationProvider) {

	console.log("Create state <kpis>");
	$stateProvider.state("kpis", {
		url: "/kpis",
		templateUrl: "client/kpis/KpisListView.ng.html",
		controller: "KPIsListCtrl"
	});
});
