console.log("Loading <SectorsRouter.ng.js>...");

angular.module("avelon").config(function($urlRouterProvider, $stateProvider, $locationProvider) {

	console.log("Create state <bubble-sectors>");
	$stateProvider.state("bubble-sectors", {
		url: "/bubble-sectors",
	        templateUrl: "client/technicalanalysis/BubbleSectorsView.ng.html",
		controller: "BubbleSectorsController"
	});
});
