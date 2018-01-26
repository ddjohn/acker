console.log("Loading <MeasureDetailsController.ng.js>...");

angular.module("avelon").controller("MeasureDetailsCtrl", function($scope, $stateParams, $meteor) {

	console.log("Bind to collection measures");
    	$scope.measure = $meteor.object(Measures, $stateParams.measureId);
});
