console.log("ui:hue()");

import { Hue } from '/imports/api/hue/hue.js';
import { Meteor } from 'meteor/meteor';
import './hue.html';
import { ReactiveVar } from 'meteor/reactive-var';

lights = new ReactiveVar([]);

Template.hue.onCreated(function () {
	Meteor.call('hue.test', (error, result) => {
		if (error) {
		  alert(error.error);
	  } else {
		  console.log("lights:");
		  console.log(result);
		  lights.set(result);
	  }
	});
});

Template.hue.helpers({
	lights() {
		return lights.get(); 
	}
});

Template.hue.events({
    'click #testButton': function (event) {
		console.log("testButton");

		Meteor.call('hue.test', (error, result) => {
      		if (error) {
        		alert(error.error);
			} else {
				console.log("lights:");
				console.log(result);
				lights.set(result);
			}
		});
	},

	'click #onButton': function (event) {
		console.log("onButton");
		console.log(event.target.getAttribute('light'));
		
		Meteor.call('hue.on', event.target.getAttribute('light'), (error, result) => {
      		if (error) {
        		alert(error.error);
			} 
		});

		Meteor.call('hue.test', (error, result) => {
			if (error) {
			  alert(error.error);
		  } else {
			  console.log("lights:");
			  console.log(result);
			  lights.set(result);
		  }
	  });
  },

	'click #offButton': function (event) {
		console.log("offButton");
		console.log(event.target.getAttribute('light'));
		
		Meteor.call('hue.off', event.target.getAttribute('light'), (error, result) => {
      		if (error) {
        		alert(error.error);
			}
		});

		Meteor.call('hue.test', (error, result) => {
			if (error) {
			  alert(error.error);
		  } else {
			  console.log("lights:");
			  console.log(result);
			  lights.set(result);
		  }
	  });
  }
});
