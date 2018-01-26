console.log("Loading <BirthdaysListController.ng.js>...");

angular.module("avelon").controller("BirthdaysListCtrl", function($scope, $meteor) {
 
	console.log("Bind to collection birthdays");
	$scope.birthdays = $meteor.collection(Birthdays).subscribe("birthdays");
	$scope.months = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "november", "December"];
 
	$scope.remove = function(birthday) {
		$scope.birthdays.remove(birthday);
	};
 
	$scope.removeAll = function() {
		$scope.birthdays.remove();
	};

	$scope.display = function(date, month, day) {
		if(date === undefined) {
			return false;
		}

		var tokens = date.split("-");
		if(parseInt(tokens[1]) === month && parseInt(tokens[2]) === day) {
			return true;
		} 
		else {
			return false;
		}
	}
});
