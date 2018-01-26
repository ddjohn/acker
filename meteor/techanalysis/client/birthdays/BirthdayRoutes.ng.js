console.log("Loading <BirthdayRoutes.ng.js>...");

angular.module("avelon").config(function($urlRouterProvider, $stateProvider, $locationProvider) {

	console.log("Create state <birthdays>");
	$stateProvider.state("birthdays", {
		url: "/birthdays",
		templateUrl: "client/birthdays/BirthdaysListView.ng.html",
		controller: "BirthdaysListCtrl"
	});
});
