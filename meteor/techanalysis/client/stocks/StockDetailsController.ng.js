console.log("Loading <StockDetailsController.ng.js>...");

angular.module("avelon").controller("StockDetailsController", function($scope, $stateParams, $meteor) {

	console.log("StockDetailsController: Bind to collection stocks");
    	$scope.stock = $meteor.object(Stocks, $stateParams.id);
});
