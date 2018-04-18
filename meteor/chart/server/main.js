console.log('server:main()');

import { Meteor } from 'meteor/meteor';
import { Items } from '../imports/ItemsApi.js';
import { Series } from '../imports/SeriesApi.js';

Meteor.startup(() => {
	console.log('server:startup()');

	//	      useAuth: true,
	//const Items = new Mongo.Collection('items');
	//var Api = new Restivus({useDefaultAuth: true, prettyJson: true});
	//
	const Api = new Restivus({prettyJson:true, authRequired:true, authToken:'97f0ad9e24ca5e0408a269748d7fe0a0'});
	Api.addCollection(Items);
	Api.addCollection(Series);
});
