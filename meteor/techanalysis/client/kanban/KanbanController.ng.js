angular.module("avelon").controller("KanbanController", function ($scope, $meteor) {
	$scope.kanban = $meteor.collection(Kanban);
	$scope.teams = $meteor.collection(Teams);
});
