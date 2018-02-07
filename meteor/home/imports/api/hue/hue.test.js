// Tests for the behavior of the hue collection
//
// https://guide.meteor.com/testing.html

import { Meteor } from 'meteor/meteor';
import { assert } from 'chai';
import { Hue } from './hue.js';

if (Meteor.isServer) {
  describe('hue collection', function () {
    it('insert correctly', function () {
      const linkId = Hue.insert({
        title: 'meteor homepage',
        url: 'https://www.meteor.com',
      });
      const added = Hue.find({ _id: linkId });
      const collectionName = added._getCollectionName();
      const count = added.count();

      assert.equal(collectionName, 'hue');
      assert.equal(count, 1);
    });
  });
}
