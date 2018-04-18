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
		Session.set('serie', elem.currentTarget.outerText);
	},
	/*
	'click .uptime'() {
		console.log("Set uptime");
		Session.set('serie', 'uptime');
	},
	*/
});
	

Template.series.helpers({
	series() {
		console.log("series::find()");
		return Series.find({});
	},
});

