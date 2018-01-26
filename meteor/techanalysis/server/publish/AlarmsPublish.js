console.log("Loading <AlarmsPublish.js>......");

Meteor.publish("alarms", function () {
	return Alarms.find({});
});
