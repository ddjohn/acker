console.log("Loading <KpisBootstrap.js>...");
 
Meteor.startup(function () {
	if(KPIs.find().count() === 0) {
		console.log("Bootstraping kpis...");
		KPIs.insert({name: "Number of Commits", description: "Commits per day"});
		KPIs.insert({name: "Size of commits (rows)", description: "Habba habba"});
		KPIs.insert({name: "Feedback time (min)", description: "Habba habba"});
		KPIs.insert({name: "Baseline time (min)", description: "Habba habba"});
		KPIs.insert({name: "Features per day", description: "Habba habba"});
		KPIs.insert({name: "Code complexity", description: "Habba habba"});
 	}
});
