console.log("Loading <StocksBootstrap.js>...");

Meteor.startup(function () {
	if(Stocks.find().count() === 0) {
		console.log("Bootstraping stocks...");

		Stocks.insert({"name" : "Alfa Laval", "value" : "76", "code" : "SSE18634" });
		Stocks.insert({"name" : "Atlas Copco", "value" : "82", "code" : "SSE46" });
		Stocks.insert({"name" : "Hennes & Mauritz", "value" : "99", "code" : "SSE992" });
		Stocks.insert({"name" : "Industrivärden", "value" : "47", "code" : "SSE143" });
		Stocks.insert({"name" : "Kinnevik", "value" : "81", "code" : "SSE999" });
		Stocks.insert({"name" : "Mordern Time Group", "value" : "44", "code" : "SSE3599" });
		Stocks.insert({"name" : "SAAB", "value" : "84", "code" : "SSE1051" });
		Stocks.insert({"name" : "Skanska", "value" : "84", "code" : "SSE1051" });
		Stocks.insert({"name" : "Swebank", "value" : "39", "code" : "SSE120" });
		Stocks.insert({"name" : "Swedish Match", "value" : "40", "code" : "SSE361" });
	}
});
