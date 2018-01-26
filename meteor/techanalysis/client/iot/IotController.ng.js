console.log("Loading <IotController.ng.js>...");

angular.module("avelon").controller("IotCtrl", function($scope, $meteor) {
    $scope.data = [[],[],[]];
    $scope.labels = [];
    $scope.series = ["moist", "wet", "dry"];
    $scope.colours = ["#00FF00", "#0000FF", "#FF0000"];
    $scope.options = { 
	animation: false,
	pointDot: false,
	showScale: true,
	datasetStrokeWidth: 2,
	emptyDataMessage: "chart has no data",		
	bezierCurve : true,
	title: {text: "David" }, 
	axisX: {title: "David" }, 
	axisY: {title: "David" }, 
    };

    $scope.items = [
	{id: 1, label: 'moist'}, 
	{id: 2, label: 'test'},
    ];
    $scope.selected = $scope.items[0];

    $scope.days = [
	{id: 1, label: '1 day'}, 
	{id: 2, label: '2 days'}, 
	{id: 3, label: '3 days'},
	{id: 5, label: '5 days'},
	{id: 8, label: '8 days'},
    ];
    $scope.selected2 = $scope.days[2];

    $scope.dots = [
	{id:   5, label: '5 dots'}, 
	{id:  10, label: '10 dots'}, 
	{id:  50, label: '50 dots'},
	{id: 100, label: '100 dots'},
    ];
    $scope.selected3 = $scope.dots[3];
 
     var drawGraph = function drawGraph(col) {
        $scope.data[0].length = 0;
        $scope.data[1].length = 0;
        $scope.data[2].length = 0;
        $scope.labels.length = 0;
	
	var size = Math.floor(col.length / parseInt($scope.selected3.id));
        for(i = 0; i < col.length; i++) {
	    if(i % size === 0 || i == col.length-1) {
		var now = new moment(col[i].epoch);
                $scope.labels.push(now.format("dd HH:mm"));
                $scope.data[0].push(parseInt(col[i].value));
                $scope.data[1].push(450);
                $scope.data[2].push(250);
            }
        }
    };

    //drawGraph({});

    $scope.update = function() {
    	$meteor.subscribe("iotPublisher");
    	var y = Date.now()-24*60*60*1000*parseInt($scope.selected2.id);
    	$meteor.subscribe("iotPublisher", {
	   selector:{type:$scope.selected.label,epoch:{$gt:y}},
	   options:{sort:{epoch:1}}
        });
	//$scope.$apply();
    };
 
    $scope.iot = $meteor.collection(IOT);
    $scope.$watch("iot", function(col, old) {
	drawGraph(col);	
    },true);

    $scope.update();
});
