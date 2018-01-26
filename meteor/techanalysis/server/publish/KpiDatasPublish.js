console.log("Loading <KpiDatasPublish.js>......");

Meteor.publish("kpidatas", function (args) {
	return KPIdatas.find(args.selector, args.options);
});
