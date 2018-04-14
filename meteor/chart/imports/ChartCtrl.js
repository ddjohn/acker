import { Session } from 'meteor/session';
import { Template     } from 'meteor/templating';

import './ChartView.html';
import { Items } from './ItemsApi.js';

//-----------------------------------

function buildChart() {
	console.log('buildChart()');

	new Chartist.Line('.ct-chart', {
		labels: Session.get('labels'),
		series: [ 
			[1,2,3],
			[2,2,3,3],
			Session.get('items'),
		]
	},{
		fullWidth: true,
		chartPadding: {right: 40}
	});
};

Template.chart.onCreated(function bodyOnCreated() {
	console.log("chart::onCreated()");
	Meteor.subscribe('items');
});

Template.chart.helpers({
	items() {
		Session.set('items', Items.find().fetch().map(x => x.value));
		Session.set('labels', Items.find().fetch().map(x => x._id));
		buildChart();
		return Items.find({});
	},
});

Template.chart.onRendered(function() {
	Tracker.autorun(function () {
		buildChart();
	});
});
