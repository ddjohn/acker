console.log("server:publications()");

import { Meteor } from 'meteor/meteor';
import { Hue } from '../hue.js';

Meteor.publish('hue.all', function () {
  console.log("publications");
  
  //return Hue.find();

  return [
    {text: 'aaa'},
    {text: 'bbb'},
    {text: 'ccc'},
  ]
});

Meteor.publish('hue.test', function () {
  console.log("publications");
  return Hue.find();
});
