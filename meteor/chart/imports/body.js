import { Template     } from 'meteor/templating';
import { ReactiveDict } from 'meteor/reactive-dict';

import './body.html';

import './NavView.html';
import './SeriesView.html';
import './SeriesCtrl.js';
import './ChartView.html';
import './ChartCtrl.js';
import './FooterView.html';

import { Items } from './ItemsApi.js';
import { Series } from './SeriesApi.js';

//-----------------------------------

Template.body.onCreated(function bodyOnCreated() {
	console.log('body::onCreated()');
//	Meteor.subscribe('items');
});

