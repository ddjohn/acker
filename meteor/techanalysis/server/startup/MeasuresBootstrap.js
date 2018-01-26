console.log("Loading <MeasuresBootstrap.js>...");
 
Meteor.startup(function () {
	if(Measures.find().count() === 0) {
		console.log("Bootstraping measures...");
		Measures.insert({icon: "envelope", name: "Active", value: 7, status: "acive"});
		Measures.insert({icon: "pencil", name: "Success", value: 7, status: "success"});
		Measures.insert({icon: "music", name: "Info", value: 7, status: "info"});
		Measures.insert({icon: "search", name: "Warning", value: 7, status: "warning"});
		Measures.insert({icon: "star", name: "Danger", value: 7, status: "danger"});
 	}
});
