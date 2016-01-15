/* global define:false */
define(function () {
  'use strict';

  var c = document.getElementById('solitaire-gg-config');
  if(c === undefined || c === null) {
    throw new Error('NoConfigurationException');
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

  var qs = (function(a) {
    if(a === '') {
      return {};
    }
    var b = {};
    for (var i = 0; i < a.length; ++i) {
      var p=a[i].split('=', 2);
      if(p.length === 1) {
        b[p[0]] = '';
      } else {
        b[p[0]] = decodeURIComponent(p[1].replace(/\+/g, ' '));
      }
    }
    return b;
  })(window.location.search.substr(1).split('&'));

  if(qs.game !== undefined) {
    cfg.rules = qs.game;
  }

  return cfg;
});
