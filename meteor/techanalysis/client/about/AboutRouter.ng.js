console.log("Loading <AboutRouter.ng.js>...");

angular.module("avelon").config(function($urlRouterProvider, $stateProvider, $locationProvider) {
    
    console.log("Create state <about>");
    $stateProvider.state("about", {
        url: "/about",
        templateUrl: "client/about/AboutView.ng.html"
    });
});
