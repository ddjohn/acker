console.log("Loading <GridsterRouter.ng.js>...");

angular.module("avelon").config(function($urlRouterProvider, $stateProvider, $locationProvider) {
    
    console.log("Create state <gridster>");
    $stateProvider.state("gridster", {
        url: "/gridster",
        templateUrl: "client/gridster/GridsterView.ng.html",
	controller: "GridsterController"
    });
});
