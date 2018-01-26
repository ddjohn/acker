console.log("Loading <ChatsBootstrap.js>...");
 
Meteor.startup(function () {
	var iso = new Date().toISOString();
	var now = Math.floor(new Date()/1000);

	if(Chats.find().count() === 0) {
		console.log("Bootstraping chats...");
		Chats.insert({epoch: now, time: iso, from: "unknown", body: "Hello world"});
		Chats.insert({epoch: now, time: iso, from: "unknown", body: "Bye world"});
 	}
});
