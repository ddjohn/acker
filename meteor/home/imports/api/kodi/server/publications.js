console.log("server:publications()");

import { Meteor } from 'meteor/meteor';
import { Kodi } from '../kodi.js';

Meteor.publish('kodi.all', function () {
  console.log("publications");
  
  //return Hue.find();

  return [
    {text: 'aaa'},
    {text: 'bbb'},
    {text: 'ccc'},
  ]
});

Meteor.publish('kodi.test', function () {
  console.log("publications");
  return Hue.find();
});
