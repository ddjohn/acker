console.log("client:routes()");

import { FlowRouter } from 'meteor/kadira:flow-router';
import { BlazeLayout } from 'meteor/kadira:blaze-layout';

// Import needed templates
import '../../ui/layouts/body/body.js';
import '../../ui/pages/home/home.js';
import '../../ui/pages/not-found/not-found.js';

// Set up all routes in the app
FlowRouter.route('/', {
  name: 'App.home',
  action() {
    BlazeLayout.render('App_body', { main: 'About_home' });
  },
});

FlowRouter.route('/about', {
  name: 'About.home',
  action() {
    BlazeLayout.render('App_body', { main: 'About_home' });
  },
});

FlowRouter.route('/hue', {
  name: 'Hue.home',
  action() {
    BlazeLayout.render('App_body', { main: 'Hue_home' });
  },
});

FlowRouter.route('/kodi', {
  name: 'Kodi.home',
  action() {
    BlazeLayout.render('App_body', { main: 'Kodi_home' });
  },
});

FlowRouter.notFound = {
  action() {
    BlazeLayout.render('App_body', { main: 'App_notFound' });
  },
};
