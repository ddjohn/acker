console.log("api:methods()");

import { Meteor } from 'meteor/meteor';
import { check } from 'meteor/check';
import { Kodi } from './kodi.js';
import { HTTP } from 'meteor/http';

const JSONRPC = "http://192.168.1.86:8080/jsonrpc";

function jsonrpc(request) {
  console.log("api:methods(kodi." + request.method + ")");
  const result = HTTP.call("POST", JSONRPC, {data:request});
  console.log(result.data)
  return result.data;
};

Meteor.methods({

  'kodi.pause'() {
    jsonrpc({"jsonrpc":"2.0","method":"Player.PlayPause","id":1,"params":{"playerid":1, "play":false}});
  },

  'kodi.play'() {
    jsonrpc({"jsonrpc":"2.0","method":"Player.PlayPause","id":1,"params":{"playerid":1, "play":true}});
  },

  'kodi.stop'() {
    jsonrpc({"jsonrpc":"2.0","method":"Player.Stop","id":1,"params":{"playerid":1}});
  },

  'kodi.home'() {
    jsonrpc({"jsonrpc":"2.0","method":"Input.Home","id":1});
  },

  'kodi.left'() {
    jsonrpc({"jsonrpc":"2.0","method":"Input.Left","id":1});
  },

  'kodi.right'() {
    jsonrpc({"jsonrpc":"2.0","method":"Input.Right","id":1});
  },

  'kodi.up'() {
    jsonrpc({"jsonrpc":"2.0","method":"Input.Up","id":1});
  },

  'kodi.down'() {
    jsonrpc({"jsonrpc":"2.0","method":"Input.Down","id":1});
  },

  'kodi.select'() {
    jsonrpc({"jsonrpc":"2.0","method":"Input.Select","id":1});
  },

});
