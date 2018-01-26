console.log("Loading <StockRsiController.ng.js>...");

angular.module("avelon").controller("StockRsiController", function($scope, $stateParams, $meteor) {
	$scope.id = $stateParams.id;
	$scope.days = $stateParams.days;
	$scope.key = "stock-rsi";

	$scope.chart = {};
	$scope.chart.type = "LineChart";
	//$scope.chart.options = {title: "Title", backgroundColor: "#f0f0f0",curveType: "function"};
	$scope.chart.data = 
		{cols: [
			{id: "DateId", label: "DateLabel", type: "date"},
			{id: "StockId", label: $stateParams.id, type: "number"},
			],
		rows: []
		};

	Meteor.call("getDataSeries", $stateParams.id, $stateParams.days, function(error, resp) {

		$scope.$apply(function() {
			$scope.serie = resp;

			$scope.chart.options = {
				title: "RSI for " + resp.hi[0]["@insnm"], 
                                legend:                 {position: "bottom"},
				backgroundColor: "#f0f0f0"
			};

//                      if(resp.hi[resp.hi.length-1]["@dt"] === resp.hi[resp.hi.length-2]["@dt"]) {
 //                               resp.hi.splice(resp.hi.length-1, 1);
  //                      };


			resp.hi.forEach(function(entry, index) {

				$scope.chart.data.rows.push({c: [
					{v: new Date(entry["@dt"])}, 
					{v: entry["@rsi"]},
				]});
			});
		});
	});
});

