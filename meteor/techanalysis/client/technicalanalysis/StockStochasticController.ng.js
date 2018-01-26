console.log("Loading <StockStochasticController.ng.js>...");

angular.module("avelon").controller("StockStochasticController", function($scope, $stateParams, $meteor) {
	$scope.id = $stateParams.id;
	$scope.days = $stateParams.days;
	$scope.key = "stock-stochastic";

	$scope.alerts = [];
	$scope.chart = {};
	$scope.chart.type = "LineChart";
	$scope.chart.data = 
		{cols: [
			{id: "DateId", label: "DateLabel", type: "date"},
			{id: "Fsto", label: "First", type: "number"},
			{id: "Ssto", label: "Second", type: "number"},
			{id: "Tsto", label: "Third", type: "number"},
			{id: "Thirty", label: "Buy", type: "number"},
			{id: "Seventy", label: "Sell", type: "number"},
			],
		rows: []
		};

	Meteor.call("getDataSeries", $stateParams.id, $stateParams.days, function(error, resp) {

		$scope.$apply(function() {
			$scope.serie = resp;
	
			$scope.chart.options = {
                                title: 			"Stochastics for " + resp.hi[0]["@insnm"],
                                backgroundColor: 	"#f0f0f0",
                                legend: 		{position: "bottom"},
				curveType: 		"function",
                        };


			if(resp.hi[resp.hi.length-1]["@cp"] > 70) {
                                $scope.alerts.push(resp.hi[0]["@insnm"] + ": Sell on" + new Date(resp.hi[resp.hi.length-1]["@dt"]));
			};

			if(resp.hi[resp.hi.length-1]["@cp"] < 30) {
                                $scope.alerts.push(resp.hi[0]["@insnm"] + ": Buy on" + new Date(resp.hi[resp.hi.length-1]["@dt"]));
			};

//                      if(resp.hi[resp.hi.length-1]["@dt"] === resp.hi[resp.hi.length-2]["@dt"]) {
 //                               resp.hi.splice(resp.hi.length-1, 1);
  //                      };


			resp.hi.forEach(function(entry, index) {

				$scope.chart.data.rows.push({c: [
					{v: new Date(entry["@dt"])}, 
					{v: entry["@fsto"]},
					{v: entry["@ssto"]},
					{v: entry["@tsto"]},
					{v: 30},
					{v: 70},
				]});
			});
		});
	});
});

