console.log("Loading <StockInfoController.ng.js>...");

angular.module("avelon").controller("StockInfoController", function($scope, $stateParams, $meteor) {

	$scope.id = $stateParams.id;
	$scope.days = $stateParams.days;
	$scope.key = "stock-info";

	$scope.instrument = {};
	Meteor.call("getInstrument", $stateParams.id, function(error, resp) {
		$scope.$apply(function() {
			$scope.instrument = resp;
		});
	});

	$scope.data = {};
	Meteor.call("getChangeData", $stateParams.id, function(error, resp) {
		$scope.$apply(function() {
			$scope.data = resp;
		});
	});

	$scope.issuer = {};
	Meteor.call("getIssuer", $stateParams.id, function(error, resp) {
		$scope.$apply(function() {
			$scope.issuer = resp;
		});
	});
});

