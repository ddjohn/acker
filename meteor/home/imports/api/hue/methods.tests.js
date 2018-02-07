// Tests for hue methods
//
// https://guide.meteor.com/testing.html

import { Meteor } from 'meteor/meteor';
import { assert } from 'chai';
import { Hue } from './hue.js';
import './methods.js';

if (Meteor.isServer) {
  describe('hue methods', function () {
    beforeEach(function () {
      Hue.remove({});
    });

    it('can add a new link', function () {
      const addLink = Meteor.server.method_handlers['hue.insert'];

      addLink.apply({}, ['meteor.com', 'https://www.meteor.com']);

      assert.equal(Hue.find().count(), 1);
    });
  });
}
