console.log("Loading <IotRoutes.js>...");

Router.route("/iot/:type/:value", function() {
	console.log(this.request.query);

	var type = this.params.type; 
	var value = this.params.value; 

	IOT.insert({epoch: Date.now(), type: type, value: value});
	KPI.upsert({name:type}, {$set: {epoch:Math.floor(new Date()/1000), value:value, icon:"leaf", status:"success"}});

	this.response.setHeader("Content-Type", "application/json");
	this.response.statusCode = 200;
	this.response.end(JSON.stringify({status:"ok"}));
}, {where: "server"});
