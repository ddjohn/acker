console.log("Loading <StockCandlestickController.ng.js>...");

angular.module("avelon").controller("StockCandlestickController", function($scope, $stateParams, $meteor) {

	$scope.id = $stateParams.id;
	$scope.days = $stateParams.days;
	$scope.key = "stock-candlestick";

	$scope.alerts = [];
	$scope.chart = {};
	//$scope.chart.type = "CandlestickChart";
	$scope.chart.type = "ComboChart";
	//$scope.chart.options = {title: "Title", backgroundColor: "#f0f0f0", seriesType: "candlesticks", series: {}};
	//$scope.chart.options = {title: "Title", backgroundColor: "#f0f0f0"};
	$scope.chart.data = 
		{cols: [
			{id: "DateId", label: "DateLabel", type: "date"},
			{id: "Low", label: $stateParams.id, type: "number"},
			{id: "Open", label: $stateParams.id, type: "number"},
			{id: "Close", label: $stateParams.id, type: "number"},
			{id: "High", label: $stateParams.id, type: "number"},
			{id: "DonHi", label: "DonHi(20)", type: "number"},
			{id: "DonLo", label: "DonLo(20)", type: "number"},
			],
		rows: []
		};

	Meteor.call("getDataSeries", $stateParams.id, $stateParams.days, function(error, resp) {

		$scope.$apply(function() {

			$scope.chart.options = {
                                title:		"Candlestick for " + resp.hi[0]["@insnm"],
				legend:		"bottom",
				candlestick:	{fallingColor:{strokeWidth:1,fill:"red"},risingColor:{strokeWidth:1,fill:"green"}},
				bar:		{groupWidth: "100%"},
				seriesType: 	"candlesticks", 
				series: 	{1:{type:"line"}, 2:{type:"line"}},
				colors:		["black","green","red"],
				backgroundColor: "#f0f0f0",
				vAxis:		{viewWindowMode:"maximized"},
			};


//			if(resp.hi[resp.hi.length-1]["@dt"] === resp.hi[resp.hi.length-2]["@dt"]) {
//				resp.hi.splice(resp.hi.length-1, 1);
//			};

			var value = 20;
			resp.hi.forEach(function(entry, index) {

				var high = NaN;
				var low = NaN;
				if(index >= value) {
					high = resp.hi[index]["@hp"];
					low = resp.hi[index]["@lp"];
				};

				if(index >= value) {
					for(var i = index-value; i < index; i++) {
						if(resp.hi[i]["@hp"] > high) high = resp.hi[i]["@hp"];
						if(resp.hi[i]["@lp"] < low) low = resp.hi[i]["@lp"];
					}
				};

				if(resp.hi.length-1 == index && entry["@hp"] == high) {
					$scope.alerts.push("Buy on " + new Date(entry["@dt"]));
				};

				if(resp.hi.length-1 == index && entry["@lp"] == low) {
					$scope.alerts.push("Sell on " + new Date(entry["@dt"]));
				};

				$scope.chart.data.rows.push({c: [
					{v: new Date(entry["@dt"])}, 
					{v: entry["@lp"]}, 
					{v: entry["@op"]}, 
					{v: entry["@cp"]}, 
					{v: entry["@hp"]},
					{v: high},
					{v: low},
				]});
			});
		});
	});
});
