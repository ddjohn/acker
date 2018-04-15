import { Session } from 'meteor/session';
import { Template     } from 'meteor/templating';

import './SeriesView.html';
import { Series } from './SeriesApi.js';

//-----------------------------------

Template.series.onCreated(function bodyOnCreated() {
	console.log("series::onCreated()");
	Meteor.subscribe('series');
});

Template.series.events({
	'click': function(elem) {
		console.log("click demon");
		console.log(elem);
	},
	'click .Demon'() {
		console.log("Set demon");
		Session.set('serie', 'demon');
	},
	'click .Demo'() {
		console.log("Set demo");
		Session.set('serie', 'demo');
	},
});
	

Template.series.helpers({
	series() {
		console.log("series::find()");
		return Series.find({});
	},
});

