console.log("Setting MAIL_URL");
process.env.MAIL_URL = "smtp://smtp465.gateway:gat3way7@smtp.gmail.com:465/";

Email.send({
	from:		"ddjohn@gmail.com",
	to:		"ddjohn@gmail.com",
	subject: 	"Meteor server restarted",
	html:		"<H1>Meteor serer restarted</h1>",

});

