import { Template } from 'meteor/templating';
import { ReactiveVar } from 'meteor/reactive-var';

import './main.html';

Template.hello.onCreated(function helloOnCreated() {
  // counter starts at 0
  this.counter = new ReactiveVar(0);
});

Template.hello.helpers({
  counter() {
    return Template.instance().counter.get();
  },
});

Template.hello.events({
  'click button'(event, instance) {
    // increment the counter when button is clicked
    instance.counter.set(instance.counter.get() + 1);
  },
});

Template.stats_chart.rendered = function(){
    new Chartist.Line('.ct-chart', {
            labels: ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday'],
            series: [
	                [12, 9, 7, 8, 5],
	                [2, 1, 3.5, 7, 3],
	                [1, 3, 4, 5, 6]
	            ]
        }, {
            fullWidth: true,
            chartPadding: {
	                right: 40
            }
    });
}
