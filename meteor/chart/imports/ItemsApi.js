console.log("api::items()");

import { Mongo } from 'meteor/mongo';
 
export const Items = new Mongo.Collection('items');

if(Meteor.isServer) {
	console.log("api::items::publish()");

	Meteor.publish('items', function itemsPublication() {
		console.log("api::items()");

		return Items.find();
	});
}
