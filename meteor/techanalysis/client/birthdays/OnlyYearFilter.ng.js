console.log("Loading <OnlyYearFilter.ng.js>...");

angular.module("avelon").filter("onlyYear", function() {

	return function(date) {
		if(date === undefined) {
                    return "";
                }
		var res = date.split("-", 1);
		return res[0];
	};
});

