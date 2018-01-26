console.log("Loading <AlarmRoutes.ng.js>...");

angular.module("avelon").config(function($urlRouterProvider, $stateProvider, $locationProvider) {

	console.log("Create state <alarms>");
	$stateProvider.state("alarms", {
		url: "/alarms",
	        templateUrl: "client/alarms/AlarmsListView.ng.html",
		controller: "AlarmsListCtrl"
	});
});
