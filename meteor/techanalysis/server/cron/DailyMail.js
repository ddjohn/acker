SyncedCron.add({
	name: "Crunch some important numbers for the marketing department",
	schedule: function(parser) {
		return parser.text("every 6 hours");
	},

	job: function() {
		Email.send({
			from:		"ddjohn@gmail.com",
			to:		"ddjohn@gmail.com",
			subject: 	"Test",
			html:		"<H1>Bye</h1>",
		});
		return 3;
	}
});

SyncedCron.start();

