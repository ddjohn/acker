console.log("Loading <KpiListController.ng.js>...");

angular.module("avelon").controller("KPIsListCtrl", function($scope, $meteor) {
 
	$scope.data = [[]];
	$scope.labels = [];
	$scope.series = [];

	console.log("Bind to collection kpis");
	$meteor.subscribe("kpidatas", {selector:{repo:"ncmain"},options:{limit:10}}).then(function(subscription) {
		$scope.data[0].length = 0;
		$scope.labels.length = 0;
		$scope.series.length = 0;
	
		var tmp = $meteor.collection(KPIdatas);
		//$scope.series.push(tmp[0].repo);
		for(i in tmp) {
			if(typeof tmp[i].occurances !== "undefined") {
			try {
				//$scope.labels.push(tmp[i].date);
				$scope.labels.push(".");
				$scope.data[0].push(parseInt(tmp[i].occurances));
			} catch(e) {}
			}
		}
	});

	$scope.myvalue = 50;
});
