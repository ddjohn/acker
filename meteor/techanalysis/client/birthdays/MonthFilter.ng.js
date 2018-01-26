console.log("Loading <MonthFilter.ng.js>...");

angular.module("avelon").filter("monthFilter", function() {

	var months = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "november", "December"];

	return function(month) {
		return months[month - 1];
	};
});

