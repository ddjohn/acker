console.log("api:methods()");

import { Meteor } from 'meteor/meteor';
import { check } from 'meteor/check';
import { Hue } from './hue.js';
import { HTTP } from 'meteor/http';

Meteor.methods({
  'hue.test'() {
    console.log("api:methods(hue.test)");

    const result = HTTP.call("GET", "http://192.168.1.66/api/D8ZXTiVIT5Jxu7sW5WI7Mnj9C60l0i94wZF3nVzh/lights");
    console.log(result.data);

    const array = [];
    for(const item in result.data) {
      result.data[item]["id"] = item;
      array.push(result.data[item]);
    }
    console.log(array);
    return array;

    /*
    HTTP.call( 'POST', 'http://127.0.0.1:1234', {
  param: {
    'username':'satoshi',
    'method':'genes',
    'ipaddr':'somePrivateUrl',
    'port': 3298
  }
}, function( error, response ) {
  if ( error ) {
    console.log( error );
  } else {
    console.log( response );
  }
});
*/
  },

  'hue.on'(num) {
    console.log("api:methods(hue.on)");

    const result = HTTP.call("PUT", 
      "http://192.168.1.66/api/D8ZXTiVIT5Jxu7sW5WI7Mnj9C60l0i94wZF3nVzh/lights/" + num + "/state", {data: {"on":true}});
    console.log(result.data);
  },

  'hue.off'(num) {
    console.log("api:methods(hue.on)");

    const result = HTTP.call("PUT", 
      "http://192.168.1.66/api/D8ZXTiVIT5Jxu7sW5WI7Mnj9C60l0i94wZF3nVzh/lights/" + num + "/state", {data: {"on":false}});
    console.log(result.data);
  },
});
