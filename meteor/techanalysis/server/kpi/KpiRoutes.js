console.log("Loading <KpiRoutes.js>...");

Router.route("/kpi/:name/:icon/:value/:status", function() {
	console.log(this.request.query);

	var name = this.params.name; 
	var icon = this.params.icon; 
	var value = this.params.value; 
	var status = this.params.status; 

	KPI.upsert({name:name}, {$set: {epoch:Math.floor(new Date()/1000), value:value, icon:icon, status:status}});

	this.response.setHeader("Content-Type", "application/json");
	this.response.statusCode = 200;
	this.response.end(JSON.stringify({status:"ok"}));
}, {where: "server"});
