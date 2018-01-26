console.log("Loading <AlarmsBootstrap.js>...");
 
Meteor.startup(function () {
	if(Alarms.find().count() === 0) {
		console.log("Bootstraping alarms...");
		Alarms.insert({date: new Date(), title: "Active", status: "acive"});
		Alarms.insert({date: new Date(), title: "Success", status: "success"});
		Alarms.insert({date: new Date(), title: "Info", status: "info"});
		Alarms.insert({date: new Date(), title: "Warning", status: "warning"});
		Alarms.insert({date: new Date(), title: "Danger", status: "danger"});
 	}
});
