console.log("Loading <StockMarketsController.ng.js>...");

angular.module("avelon").controller("StockMarketsController", function($scope, $meteor) {
	$scope.test = {};
	Meteor.call("getMarkets", function(error, resp) {
		$scope.$apply(function() {
			$scope.test = resp;
		});
	});
});

