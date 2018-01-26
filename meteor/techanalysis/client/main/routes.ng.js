console.log("Loading <routes.ng.js>...");

angular.module("avelon").config(function($urlRouterProvider, $stateProvider, $locationProvider) {

    console.log("Set HTML5 mode");
    $locationProvider.html5Mode(true);

    console.log("Set default router");
    $urlRouterProvider.otherwise("/about");
});

