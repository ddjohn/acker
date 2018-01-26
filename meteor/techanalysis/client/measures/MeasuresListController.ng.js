console.log("Loading <MeasuresListController.ng.js>...");

angular.module("avelon").controller("MeasuresListCtrl", function($scope, $meteor) {
 
	console.log("Bind to collection measures");
	$scope.measures = $meteor.collection(Measures).subscribe("measures", {sort: {name: 1}});

/*
	$scope.measures = $meteor.collection(function() {
		return Measures.find({}, {sort: {name: 1}})
	});
*/ 
	$scope.remove = function(measure){
		$scope.measures.remove(measure);
	};
 
	$scope.removeAll = function(){
		$scope.measures.remove();
	};
});
