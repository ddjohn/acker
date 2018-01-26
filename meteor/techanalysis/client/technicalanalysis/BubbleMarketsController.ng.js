console.log("Loading <MarketsController.ng.js>...");

angular.module("avelon").controller("BubbleMarketsController", function($scope, $stateParams, $meteor) {

	$scope.chart = {};
	$scope.chart.type = "BubbleChart";
	$scope.chart.data = { cols: [
		{id: "Stock", label: "Stock", type: "string"},
		{id:     "X", label:     "X", type: "number"},
		{id:     "Y", label:     "Y", type: "number"},
		{id: "Group", label: "Group", type: "string"},
		{id: "Value", label: "Value", type: "number"},
		], rows: [] };

	Meteor.call("getMarketsInstruments", function(error, stocks) {
		for(i in stocks) {
			Meteor.call("getDataSeries", stocks[i], 0, function(error, change) {
				$scope.$apply(function() {
					$scope.chart.data.rows.push({c: [ 
						{v: change.hi[0]["@ins"]}, 
						{v: change.hi[0]["@ssto"]}, 
						{v: change.hi[0]["@rsi"]}, 
						{v: change.hi[0]["@ins"]}, 
						{v: 5} 
					]});
				});
			});
		}
	});
});
