console.log("api:methods()");

import { Meteor } from 'meteor/meteor';
import { check } from 'meteor/check';
import { Kodi } from './kodi.js';
import { HTTP } from 'meteor/http';

Meteor.methods({

  'kodi.pause'() {
    console.log("api:methods(kodi.pause)");
    const result = HTTP.call("POST", 
      "http://192.168.1.86:8080/jsonrpc?Player.PlayPause", 
      {data:{"jsonrpc":"2.0","method":"Player.PlayPause","id":1,"params":{"playerid":1}}});
    console.log(result.data);
  },

  'kodi.play'() {
    console.log("api:methods(kodi.play)");
    const result = HTTP.call("POST", 
      "http://192.168.1.86:8080/jsonrpc?Player.PlayPause", 
      {data:{"jsonrpc":"2.0","method":"Player.PlayPause","id":1,"params":{"playerid":1}}});
    console.log(result.data);
  },

  'kodi.stop'() {
    console.log("api:methods(kodi.stop)");
    const result = HTTP.call("POST", 
      "http://192.168.1.86:8080/jsonrpc?Player.PlayPause", 
      {data:{"jsonrpc":"2.0","method":"Player.PlayPause","id":1,"params":{"playerid":1}}});
    console.log(result.data);
  },

});
