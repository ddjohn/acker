
SyncedCron.add({
	name: "Water Mail",
	schedule: function(parser) {
		return parser.text("every 6 hours");
	},

	job: function() {
		var value = IOT.find({}, {sort: {epoch: -1}, limit: 1}).fetch();
		if(value[0].value < 300) {
		Email.send({
			from:		"ddjohn@gmail.com",
			to:		"ddjohn@gmail.com",
			subject: 	"Water your plants",
			html:		"<H1>Please water your plants!!</h1>Value: " + value[0].value,
		});
		}
		return 3;
	}
});

SyncedCron.start();

