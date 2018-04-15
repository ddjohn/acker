console.log("api::items()");

import { Mongo } from 'meteor/mongo';
 
export const Items = new Mongo.Collection('items');

if(Meteor.isServer) {
	console.log("api::items::publish()");

	Meteor.publish('items', function itemsPublication() {
		console.log("api::items()");
		//console.log("api::items() -  name " + name);

		//if(name === undefined) {
			return Items.find({});
		//} else {
		//	return Items.find({"name":name});
		//}
	});
}
