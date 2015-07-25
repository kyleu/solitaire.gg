/* global define:false */
define(function () {
  'use strict';

  var c = document.getElementById('solitaire-gg-config');
  if(c === undefined) {
    throw 'NoConfigurationException';
  }
  var cfg = JSON.parse(c.textContent);

  if(document.location.href.indexOf('https') === 0) {
    cfg.wsUrl = 'wss://' + document.location.host + '/websocket';
  } else {
    cfg.wsUrl = 'ws://' + document.location.host + '/websocket';
  }

  if(document.location.href.indexOf('http') === 0) {
    cfg.assetRoot = '/assets/';
  } else {
    cfg.assetRoot = 'assets/';
  }

  return cfg;
});
