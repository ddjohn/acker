angular.module("avelon").config(function($urlRouterProvider, $stateProvider, $locationProvider) {
	$stateProvider.state("kanban", {
		url: "/kanban",
		templateUrl: "client/kanban/KanbanView.ng.html",
		controller: "KanbanController"
        });
});

