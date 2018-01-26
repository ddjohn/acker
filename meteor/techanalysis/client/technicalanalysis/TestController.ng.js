console.log("Loading <TestController.ng.js>...");

angular.module("avelon").controller("TestCtrl9", function($scope, $stateParams, $meteor) {
	$scope.id = $stateParams.id;
	$scope.days = $stateParams.days;
	$scope.key = "donchian";

	$scope.chart = {};
	$scope.chart.type = "LineChart";
	$scope.chart.options = {title: "Title", backgroundColor: "#f0f0f0"};
	$scope.chart.data = 
		{cols: [
			{id: "DateId", label: "DateLabel", type: "date"},
			{id: "High", label: $stateParams.id, type: "number"},
			{id: "Low", label: $stateParams.id, type: "number"},
			{id: "Stock", label: $stateParams.id, type: "number"},
			],
		rows: []
		};

	Meteor.call("getDataSeries", $stateParams.id, $stateParams.days, function(error, resp) {

		$scope.$apply(function() {
			$scope.serie = resp;
			for(var i = 0; i < resp.hi.length; i++) {
				console.log(resp.hi[i]);
			}	
			resp.hi.forEach(function(entry) {
				$scope.chart.data.rows.push({c: [
					{v: new Date(entry["@dt"])}, 
					{v: entry["@cp"]},
					{v: entry["@lp"]},
					{v: entry["@cp"]},
				]});
			});
		});
	});
});

