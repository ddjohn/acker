console.log("Loading <KpisPublish.js>......");

Meteor.publish("kpis", function () {
	return KPIs.find({});
});
