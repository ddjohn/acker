console.log("Loading <IotPublish.js>......");

Meteor.publish("iotPublisher", function (args) {
	if(args) {
		console.log(args);
	        return IOT.find(args.selector, args.options);
	}
	else {
		console.log("Clear subscriptions");
		this.stop();
	}
});


