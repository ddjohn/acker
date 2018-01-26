console.log("Loading <StocksPublish.js>......");

Meteor.publish("stocks", function () {
	return Stocks.find({});
});
