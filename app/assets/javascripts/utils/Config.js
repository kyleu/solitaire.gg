/* global define:false */
define(function () {
  'use strict';

  var c = document.getElementById('solitaire-gg-config');
  if(c === undefined) {
    throw 'NoConfigurationException';
  }
  var json = JSON.parse(c.innerHTML);
  if(document.location.href.indexOf('https') === 0) {
    json.wsUrl = 'wss://' + json.host + '/websocket';
  } else {
    json.wsUrl = 'ws://' + json.host + '/websocket';
  }
  return json;
});
