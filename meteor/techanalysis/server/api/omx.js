var myHost = "www.nasdaqomxnordic.com";
var myPort = 80;
var myPath = "/webproxy/DataFeedProxy.aspx?Json=1&"
var myUrl = "http://" + myHost + ":" + myPort + myPath;

Future = Npm.require("fibers/future");

Meteor.methods({
	getMarkets: function() {
		var future = new Future();
		OMX.methods.omx.getMarkets(function(resp) {
		       future.return(resp);
		});
		return future.wait();
	},
	getChangeData: function(id) {
		var future = new Future();
		OMX.methods.omx.getChangeData(id, function(resp) {
		       future.return(resp);
		});
		return future.wait();
	},
	getInstrument: function(id) {
		var future = new Future();
		OMX.methods.omx.getInstrument(id, function(resp) {
		       future.return(resp);
		});
		return future.wait();
	},
	getIssuer: function(id) {
		var future = new Future();
		OMX.methods.omx.getIssuer(id, function(resp) {
		       future.return(resp);
		});
		return future.wait();
	},
	getDataSeries: function(id, days) {
		var future = new Future();

		var n = new Date();
		var now  = n.toISOString().substr(0,10);
		var then = (new Date(n - days*24*3600*1000)).toISOString().substr(0,10);
		OMX.methods.omx.getDataSeries(id, then, now, function(resp) {
                      if(resp.hi[resp.hi.length-1]["@dt"] === resp.hi[resp.hi.length-2]["@dt"]) {
                                resp.hi.splice(resp.hi.length-1, 1);
                        };

		       future.return(resp);
		});
		return future.wait();
	},

	getOmxInstrument: function() {
		return "SE0001809476";
	},
	getOmx30Instrument: function() {
		return [
			"SSE3966", "SSE18634", "SSE402" , "SSE45" , "SSE3524" , "SSE15285", "SSE81"  , "SSE101" , "SSE812" , "SSE992", 
			"SSE161" , "SSE22335", "SSE3599", "SSE220", "SSE39854", "SSE4928" , "SSE323" ,            "SSE281" , "SSE401",
			"SSE340" , "SSE283"  , "SSE285" , "SSE300", "SSE120"  , "SSE361"  , "SSE1027", "SSE5095", "SSE366"     
		];
			//"SSE161" , "SSE22335", "SSE3599", "SSE220", "SSE39854", "SSE4928" , "SSE323" , "SSE261" , "SSE281" , "SSE401",
	},
	getMarketsInstruments: function() {
		return [ "SE0001809476", "DK0060368991", "SE0000337842", "FI0008900212", "IS0000018885" ];
	},
	getSectorsInstruments: function() {
		return [ "SE0004384519", "SE0004384535", "SE0004384543", "SE0004384550", "SE0004384568", "SE0004384576", 
		 	 "SE0004384584", "SE0004384592", "SE0004384600", "SE0004384618", "SE0004384626", "SE0004384634", 
			 "SE0004384659", "SE0004384667", "SE0004384683", "SE0004384691", "SE0004384717", "SE0004384733",
			 "SE0004384741", "SE0004384758", "SE0004384766", "SE0004384774", "SE0004384782" ];
	},
});

OMX = {

	methods: {
		omx: {
			getIssuer: function(stock, callback) {
				OMX.utility.getIssuer("Issuer.a=0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16&Issuer.e=1,2&Instrument=" + stock, function(resp) {
					callback(resp);
				});
			},
			getMarkets: function(callback) {
				OMX.utility.getMarket("Market=L:INET:H7057510,L:INET:H7057520,L:INET:H7057530&Inst.an=id,fnm&Market.a=2", function(resp) {
					callback(resp);
				});
			},
			getDataSeries: function(stock, thenString, nowString, callback) {
				OMX.utility.getDataSeries("Hi.a=0,1,2,3,4,8,30,31,32,33,34,37,38,39,41,42,44,45&MovingAverageDays1=20&MovingAverageDays2=60&MovingAverageDays3=200&showAdjusted=true&AppendIntraDay=yes&FromDate=" + thenString + "&ToDate=" + nowString + "&Instrument=" + stock, function(resp) {
					callback(resp);
				});
			},
			getChangeData: function(stock, callback) {
				OMX.utility.getChangeData("hi.an=wc,mc,tmc,smc,yc,fwl,fwh,ysc,ylp,yhp&Instrument=" + stock, function(resp) {
					callback(resp);
				});
			},
			getInstrument: function(stock, callback) {
				OMX.utility.getInstrument("Instrument=" + stock, function(resp) {
					callback(resp);
				});
			},
		}
	},

	utility: {
		getIssuer: function(data, callback) {
			OMX.utility.dataFeedProxy("DefaultDecimal=true&cache=skip&SubSystem=Issuer&Action=GetIssuer&" + data, function(resp) {
                                callback(resp);
                        });
		},
		getMarket: function(data, callback) {
			OMX.utility.dataFeedProxy("DefaultDecimal=true&cache=skip&SubSystem=Prices&Action=GetMarket&Exchange=NMF&" + data, function(resp) {
                                callback(resp);
                        });
		},
		getDataSeries: function(data, callback) {
			OMX.utility.dataFeedProxy("DefaultDecimal=true&cache=skip&SubSystem=History&Action=GetDataSeries&" + data, function(resp) {
                                callback(resp);
                        });
		},
		getChangeData: function(data, callback) {
			OMX.utility.dataFeedProxy("DefaultDecimal=true&cache=skip&SubSystem=History&Action=GetChangeData&" + data, function(resp) {
                                callback(resp);
                        });
		},
		getInstrument: function(data, callback) {
			OMX.utility.dataFeedProxy("DefaultDecimal=true&cache=skip&SubSystem=Prices&Action=GetInstrument&" + data, function(resp) {
                                callback(resp);
                        });
		},
		dataFeedProxy: function(data, callback) {
        		Meteor.http.get(myUrl + data, {}, function(error, resp) {
				console.log("url: " + myUrl + data);
               			console.log("error: " + error);
               			console.log("statuscode: " + resp.statusCode);
               			callback(JSON.parse(resp.content));
        		});
		}
	}
};
