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
});
