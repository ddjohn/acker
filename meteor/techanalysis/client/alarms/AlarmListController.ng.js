console.log("Loading <AlarmListController.ng.js>...");

angular.module("avelon").controller("AlarmsListCtrl", function($scope, $meteor) {
 
	console.log("Bind to collection alarms");
	$scope.alarms = $meteor.collection(Alarms).subscribe("alarms");
/*
	$scope.alarms = $meteor.collection(function() {
		return Alarms.find({}, {sort: {date: 1}})
	});
*/
 
	$scope.remove = function(alarm) {
		$scope.alarms.remove(alarm);
	};
 
	$scope.removeAll = function(){
		$scope.alarms.remove();
	};
});
