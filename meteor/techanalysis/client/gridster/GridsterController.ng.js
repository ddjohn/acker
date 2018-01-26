console.log("Loading <GridsterController.ng.js>...");

angular.module("avelon").controller("GridsterController", function($scope, $meteor, $reactive) {

    $reactive(this).attach($scope); 

    $meteor.subscribe("kpiPublisher");
    $scope.kpi = $meteor.collection(KPI);
 
    $scope.$watch("kpi", function(resp, old) {
        if (resp === old) { return; }

	var now = new Date()/1000;

	resp.forEach(function(entry, index) {
		console.log("%d: %O", index, entry);	
		if(now - entry.epoch > 300) {
			console.log("Old value");
			entry.status = "old";
		}
	});
    },true);

//    $scope.apply();
});
