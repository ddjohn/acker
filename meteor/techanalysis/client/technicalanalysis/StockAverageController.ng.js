console.log("Loading <StockAverageController.ng.js>...");

angular.module("avelon").controller("StockAverageController", function($scope, $stateParams, $meteor) {

	$scope.id = $stateParams.id;
	$scope.days = $stateParams.days;
	$scope.key = "stock-average";

	$scope.chart = {};
	$scope.chart.type = "LineChart";
	$scope.chart.data = 
		{cols: [
			{id: "DateId", label: "DateLabel", type: "date"},
			{id: "StockId", label: $stateParams.id, type: "number"},
			{id: "MovAvgMonth", label: "MovAvg 20", type: "number"},
			{id: "MovAvgQuarter", label: "MovAvg 60", type: "number"},
			{id: "MovAvgYear", label: "MovAvg 200", type: "number"}
			],
		rows: []
		};


	Meteor.call("getDataSeries", $stateParams.id, $stateParams.days, function(error, resp) {

		$scope.$apply(function() {

			$scope.chart.options = {
				title: "Moving Average for " + resp.hi[0]["@insnm"],
				backgroundColor: "#f0f0f0",
                                legend: {position: "bottom"},
				series: {2:{lineDashStyle:[4, 4]},  3:{lineDashStyle:[4, 4]}, 1:{lineDashStyle:[2, 2, 20, 2]}},
                                vAxis:          {viewWindowMode:"maximized"},
			};

//                      if(resp.hi[resp.hi.length-1]["@dt"] === resp.hi[resp.hi.length-2]["@dt"]) {
 //                               resp.hi.splice(resp.hi.length-1, 1);
  //                      };


			resp.hi.forEach(function(entry, index) {

				$scope.chart.data.rows.push({c: [
					{v: new Date(entry["@dt"])}, 
					{v: entry["@cp"]},
					{v: entry["@mvav1"]},
					{v: entry["@mvav2"]},
					{v: entry["@mvav3"]}
				]});
			});
		});
	});
});

