console.log("Loading <MeasuresPublish.js>......");

Meteor.publish("measures", function () {
	return Measures.find({});
});
