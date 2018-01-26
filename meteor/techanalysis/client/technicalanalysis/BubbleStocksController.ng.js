console.log("Loading <MarketsController.ng.js>...");

angular.module("avelon").controller("BubbleStocksController", function($scope, $stateParams, $meteor) {

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
	Meteor.call("getOmx30Instrument", function(error, stocks) {
		for(i in stocks) {
			Meteor.call("getDataSeries", stocks[i], 30, function(error, change) {

				var hi = change.hi[change.hi.length - 1];

				// Table
				var environment = parseFloat(100 * (hi["@cp"]-hi["@mvav3"]) / hi["@cp"]);
				var trend       = parseFloat(100 * (hi["@cp"]-hi["@mvav1"]) / hi["@cp"]);
				var ssto  	= parseFloat(hi["@ssto"]);
				var fsto  	= parseFloat(hi["@fsto"]);
				var rsi         = parseFloat(hi["@rsi"]);

				var range 	= Math.abs(hi["@hp"]-hi["@lp"]);

				var extra = "none";
				if( Math.abs(hi["@cp"]-hi["@lp"])/range < 0.10 ) {
					if( hi["@op"] < hi["@cp"]) {
						extra = "spike";
					} else {
						extra = "richochette";	
					}
				}

				if( Math.abs(hi["@cp"]-hi["@hp"])/range < 0.10 ) {
					extra = "reversal";	
				}

				var points = (environment>0?1:0) + (trend>0?1:0) + (ssto>30?0:1) + (ssto>20?0:1) + (fsto>10?0:1) + (rsi>30?0:1) + (extra=="richochette"?1:0);

				$scope.color.push({
					name: 		"info",
					avg200_envir: 	environment > 0 ? "success" : "danger",
					avg20_trend: 	trend > 0 ? "success" : "danger",
                                        slowk30: 	ssto > 70 ? "danger" : (ssto < 30 ? "success" : "primary"),
                                        slowk20: 	ssto > 50 ? "danger" : (ssto < 20 ? "success" : "primary"),
                                        fastk10: 	fsto > 50 ? "danger" : (fsto < 10 ? "success" : "primary"),
                                        rsi: 		rsi > 70 ? "danger" : (rsi < 30 ? "success" : "primary"),
					extra: 		extra == "richochette" ? "success" : "primary",
					points: 	"default",
				});

				$scope.data.push({
					name: 		hi["@insnm"],
					avg200_envir: 	environment.toFixed(2) + "%",
					avg20_trend: 	trend.toFixed(2) + "%",
					slowk30: 	ssto,
					slowk20: 	ssto,
					fastk10: 	fsto,
					rsi: 		rsi,
					extra: 		extra,
					points: 	points,
				});

				// Chart
				$scope.$apply(function() {
					$scope.chart.data.rows.push({c: [ 
						{v: hi["@insnm"]}, 
						{v: hi["@ssto"]}, 
						{v: trend}, 
						{v: "" + points}, 
						//{v: hi["@insnm"]}, 
						{v: points} 
					]});
				});
			});
		}
	});
});
