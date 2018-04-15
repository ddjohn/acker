import { Session } from 'meteor/session';
import { Template     } from 'meteor/templating';

import './ChartView.html';
import { Items } from './ItemsApi.js';

//-----------------------------------

Template.chart.onCreated(function bodyOnCreated() {
	console.log("chart::onCreated()");
	Session.set('serie', 'demon');
	Meteor.subscribe('items');
});

function buildChart() {
	console.log('buildChart()');

	new Chartist.Line('.ct-chart', {
		labels: Session.get('labels'),
		series: [ 
			Session.get('items'),
		],
	},{
		low: 0,
		fullWidth: true,
		chartPadding: {right: 40},
		showLine: true,
		showPoint: false,
		lineSmooth: Chartist.Interpolation.cardinal({
			tension: 0.2
		})
	});
};

Template.chart.helpers({
	items() {
		Session.set('items', Items.find({'name':Session.get('serie')}).fetch().map(x => x.value));
		//Session.set('labels', Items.find().fetch({name:Session.get('serie')}).map(x => x._id));
		buildChart();
		return Items.find({'name':Session.get('serie')});
	},
});

Template.chart.onRendered(function() {
	Tracker.autorun(function () {
		buildChart();
	});
});
