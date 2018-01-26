console.log("Loading <ChatsPublish.js>......");

Meteor.publish("chats", function () {
	return Chats.find({});
});
