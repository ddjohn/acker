console.log("Loading <StockVolumeController.ng.js>...");

angular.module("avelon").controller("StockVolumeController", function($scope, $stateParams, $meteor) {
	$scope.id   = $stateParams.id;
	$scope.days = $stateParams.days;
	$scope.key  = "stock-volume";

	$scope.alerts = [];
	$scope.chart = {};
	$scope.chart.type = "ComboChart";

	$scope.chart.data = 
		{cols: [
			{id: "DateId", label: "DateLabel", type: "date"},
			{id: "Volume", label: $stateParams.id, type: "number"},
			{id: "Average", label: "Average(5)", type: "number"},
			],
		rows: []
		};

	Meteor.call("getDataSeries", $stateParams.id, $stateParams.days, function(error, resp) {

		$scope.$apply(function() {

			$scope.chart.options = {
				title: 			"Volumes for " + resp.hi[0]["@insnm"],
				backgroundColor: 	"#f0f0f0",
                                legend: 		{position: "bottom"},
				seriesType:		"bars",
				series: 		{1: {type: 'line', curveType: "function"}}, 
				orientation: 		"horizontal",
				vAxis:			{viewWindowMode:"maximized"},
			};

//                      if(resp.hi[resp.hi.length-1]["@dt"] === resp.hi[resp.hi.length-2]["@dt"]) {
 //                               resp.hi.splice(resp.hi.length-1, 1);
  //                      };


	
			var sum = 0;
			var no = 0;
			resp.hi.forEach(function(entry, index) {

				if(index > 5) {
					sum += parseInt(entry["@tv"]);
					no++;
				}
				if(index > 10) {
					sum -= parseInt(resp.hi[index-5]["@tv"]);
					no--;
				}
				$scope.chart.data.rows.push({c: [
					{v: new Date(entry["@dt"])}, 
					{v: entry["@tv"]},
					{v: sum/no},
				]});

				if(resp.hi.length-1 == index && resp.hi[resp.hi.length-1]["@tv"]/(sum/no) > 1.2) {
	                                $scope.alerts.push(resp.hi[0]["@insnm"] + ": High revenue on " + new Date(resp.hi[resp.hi.length-1]["@dt"]));
				};	
			});
		});
	});
});

