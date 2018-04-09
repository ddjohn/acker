import { Meteor } from 'meteor/meteor';

Meteor.startup(() => {
  Items = new Mongo.Collection('items');

  var Api = new Restivus({useDefaultAuth: true, prettyJson: true});
  Api.addCollection(Items);

});
