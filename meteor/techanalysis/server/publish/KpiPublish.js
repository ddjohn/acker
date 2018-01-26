console.log("Loading <KpiPublish.js>......");

Meteor.publish("iotPublisher", function (args) {
	        return KPI.find();
});


