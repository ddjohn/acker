console.log("ui:kodi()");

import { Kodi } from '/imports/api/kodi/kodi.js';
import { Meteor } from 'meteor/meteor';
import './kodi.html';
import { ReactiveVar } from 'meteor/reactive-var';


Template.kodi.events({
    'click #playButton': function (event) {
		console.log("playButton");

		Meteor.call('kodi.play', (error, result) => {
      		if (error) {
        		alert(error.error);
			} else {
				console.log(result);
			}
		});
	},

    'click #pauseButton': function (event) {
		console.log("pauseButton");

		Meteor.call('kodi.pause', (error, result) => {
      		if (error) {
        		alert(error.error);
			} else {
				console.log(result);
			}
		});
	},

    'click #stopButton': function (event) {
		console.log("stopButton");

		Meteor.call('kodi.stop', (error, result) => {
      		if (error) {
        		alert(error.error);
			} else {
				console.log(result);
			}
		});
	},

    'click #homeButton': function (event) {
		console.log("homeButton");

		Meteor.call('kodi.home', (error, result) => {
      		if (error) {
        		alert(error.error);
			} else {
				console.log(result);
			}
		});
	},

    'click #leftButton': function (event) {
		console.log("leftButton");

		Meteor.call('kodi.left', (error, result) => {
      		if (error) {
        		alert(error.error);
			} else {
				console.log(result);
			}
		});
	},

    'click #rightButton': function (event) {
		console.log("rightButton");

		Meteor.call('kodi.right', (error, result) => {
      		if (error) {
        		alert(error.error);
			} else {
				console.log(result);
			}
		});
	},

    'click #upButton': function (event) {
		console.log("upButton");

		Meteor.call('kodi.up', (error, result) => {
      		if (error) {
        		alert(error.error);
			} else {
				console.log(result);
			}
		});
	},

    'click #downButton': function (event) {
		console.log("downButton");

		Meteor.call('kodi.down', (error, result) => {
      		if (error) {
        		alert(error.error);
			} else {
				console.log(result);
			}
		});
	},

    'click #selectButton': function (event) {
		console.log("selectButton");

		Meteor.call('kodi.select', (error, result) => {
      		if (error) {
        		alert(error.error);
			} else {
				console.log(result);
			}
		});
	},

});
