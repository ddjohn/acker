console.log("Loading <MeasuresRoutes.ng.js>...");

angular.module("avelon").config(function($urlRouterProvider, $stateProvider, $locationProvider) {

	console.log("Create state <measures>");
	$stateProvider.state("measures", {
		url: "/measures",
	        templateUrl: "client/measures/MeasuresListView.ng.html",
		controller: "MeasuresListCtrl"
	})

	.state("measureDetails", {
		url: "/measures/:measureId",
		templateUrl: "client/measures/MeasureDetailsView.ng.html",
		controller: "MeasureDetailsCtrl"
	})

	.state("external-measures", {
		url: "/measures/:name/:value/:icon/:status",
		template: "<h1>ok</h1>",
		controller: function($scope, $stateParams) {
			Measures.insert({name: $stateParams.name, value: $stateParams.value, icon: $stateParams.icon, status: $stateParams.status});
		}
	});
});
