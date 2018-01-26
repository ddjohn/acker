console.log("Loading <IotsRoutes.ng.js>...");

angular.module("avelon").config(function($urlRouterProvider, $stateProvider, $locationProvider) {

	console.log("Create state <iot>");
	$stateProvider.state("iot", {
		url: "/iot",
	        templateUrl: "client/iot/IotView.ng.html",
		controller: "IotCtrl"
	});
});
