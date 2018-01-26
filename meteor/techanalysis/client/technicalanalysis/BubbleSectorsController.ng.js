console.log("Loading <SectorsController.ng.js>...");

angular.module("avelon").controller("BubbleSectorsController", function($scope, $stateParams, $meteor) {

	$scope.chart = {};
	$scope.chart.type = "BubbleChart";
	$scope.chart.options = {title: "Stocks", vAxis: {title: "Trend (%)"}, hAxis: {title: "Stichastic (%)"}};
	$scope.chart.data = { cols: [
		{id: "Stock", label: "Stock", type: "string"},
		{id:     "X", label:     "X", type: "number"},
		{id:     "Y", label:     "Y", type: "number"},
		{id: "Group", label: "Group", type: "string"},
		{id: "Value", label: "Value", type: "number"},
		], rows: [] };

	$scope.data = [];
	$scope.color = [];
	Meteor.call("getSectorsInstruments", function(error, stocks) {
		for(i in stocks) {
			Meteor.call("getDataSeries", stocks[i], 0, function(error, change) {

				console.log(change);
				var hi = change.hi;
				//var hi = change.hi[0];

				// Chart
				$scope.$apply(function() {
					$scope.chart.data.rows.push({c: [ 
						{v: hi["@insnm"]}, 
						{v: hi["@ssto"]}, 
						{v: 100 * (hi["@cp"]-hi["@mvav1"])/hi["@cp"]}, 
						{v: hi["@ins"]}, 
						{v: 5} 
					]});
				});

				// Table
				var trend = parseFloat(100 * (hi["@mvav1"]-hi["@cp"]) / hi["@cp"]);
				var stochastic = parseFloat(hi["@ssto"]);
				var rsi = parseFloat(hi["@rsi"]);
				$scope.color.push({
					name: "info",
					trend: (trend > 0 ? "success" : "danger"),
					stochastic: (stochastic>70 ? "danger" : (stochastic < 30 ? "success" : "primary")), 
					rsi: (rsi>70 ? "danger" : (rsi < 30 ? "success" : "primary")),
				});
				$scope.data.push({
					name: hi["@insnm"],
					trend: (100 * (hi["@mvav1"]-hi["@cp"]) / hi["@cp"]).toFixed(2) + "%",
					stochastic: hi["@ssto"],
					rsi: hi["@rsi"],
				});
			});
		}
	});
});

