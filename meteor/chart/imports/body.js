import { Template     } from 'meteor/templating';
import { ReactiveDict } from 'meteor/reactive-dict';

import './body.html';
import './NavView.html';
import './ChartView.html';
import './ChartCtrl.js';
import './FooterView.html';
import { Items } from './ItemsApi.js';

//-----------------------------------

Template.body.onCreated(function bodyOnCreated() {
	console.log('body::onCreated()');
	Meteor.subscribe('items');
});

