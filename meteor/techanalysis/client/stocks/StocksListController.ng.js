console.log("Loading <StocksListController.ng.js>...");

angular.module("avelon").controller("StocksListController", function($scope, $meteor, helpers) {
 
	console.log("StocksListController: Bind to collection stocks");
	$scope.stocks = $meteor.collection(Stocks).subscribe("stocks", {sort: {name: 1}});

/*
	$scope.measures = $meteor.collection(function() {
		return Measures.find({}, {sort: {name: 1}})
	});
*/ 

	$scope.add = function(stock) {
		console.log("StocksListController: Add " + stock);
		helpers.getStock(stock.code, function(name) {
			stock.name = name;
			$scope.stocks.save(stock);
		});
		//$scope.stock.code = ""; //Not working
	};

	$scope.remove = function(stock){
		console.log("StocksListController: Remove " + stock);
		$scope.stocks.remove(stock);
	};
 
	$scope.removeAll = function(){
		console.log("StocksListController: Remove all");
		$scope.stocks.remove();
	};
});
