console.log("api::series()");

import { Mongo } from 'meteor/mongo';
 
export const Series = new Mongo.Collection('series');

if(Meteor.isServer) {
	console.log("api::series::publish()");

	Meteor.publish('series', function itemsPublication() {
		console.log("api::series()");
			return Series.find({});
	});
}
