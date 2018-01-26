console.log("Loading <BirthdaysPublish.js>......");

Meteor.publish("birthdays", function () {
	return Birthdays.find({});
});
