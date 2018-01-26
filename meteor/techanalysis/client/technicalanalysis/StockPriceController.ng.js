console.log("Loading <StockPriceController.ng.js>...");

angular.module("avelon").controller("StockPriceController", function($scope, $stateParams, $meteor) {
	$scope.id   = $stateParams.id;
	$scope.days = $stateParams.days;
	$scope.key  = "stock-price";

	$scope.chart = {};
	$scope.chart.type = "LineChart";
	$scope.alerts = [];

	Meteor.call("getDataSeries", $stateParams.id, $stateParams.days, function(error, resp) {
		$scope.$apply(function() {

			var max = 0;
			var min = 10000;
			var latest;
			var second_latest;


//                      if(resp.hi[resp.hi.length-1]["@dt"] === resp.hi[resp.hi.length-2]["@dt"]) {
 //                               resp.hi.splice(resp.hi.length-1, 1);
  //                      };


			resp.hi.forEach(function(entry, index) {

				second_latest = latest;
				if(entry["@cp"] > max) max = entry["@cp"];
				if(entry["@cp"] < min) min = entry["@cp"];
				latest = entry["@cp"];
			});

			if(resp.hi[resp.hi.length-1]["@cp"] >= resp.hi[resp.hi.length-1]["@mvav1"]) {
				$scope.alerts.push("Positive trend on " + new Date(resp.hi[resp.hi.length-1]["@dt"]));
			} else {
				$scope.alerts.push("Negative trend on " + new Date(resp.hi[resp.hi.length-1]["@dt"]));
			};

			$scope.chart.data = {
				cols: [
					{id:  "DateId", label:          "Date", type:   "date"},
					{id: "StockId", label: $stateParams.id, type: "number"},
					{id:  "MovAvg", label:   "Average(20)", type: "number"},
					{id:     "Max", label:             max, type: "number"},
					{id:     "Min", label:             min, type: "number"},
					],
				rows: []
			};

			$scope.chart.options = {
				title: "Price for " + resp.hi[0]["@insnm"] + " (" + latest + ", " + (latest-second_latest).toFixed(2) + ")", 
				backgroundColor: "#f0f0f0",
                                legend: {position: "bottom"},
				series: {2:{lineDashStyle:[4, 4]},  3:{lineDashStyle:[4, 4]}, 1:{lineDashStyle:[2, 2, 20, 2]}},
				vAxis:		{viewWindowMode:"maximized"},
			};

			resp.hi.forEach(function(entry, index) {

				$scope.chart.data.rows.push({c: [
					{v: new Date(entry["@dt"])}, 
					{v: entry["@cp"]},
					{v: entry["@mvav1"]},
					{v: max},
					{v: min},
				]});
			});
		});
	});
});

