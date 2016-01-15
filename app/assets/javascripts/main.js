/* global requirejs:false */
requirejs.config({
  baseUrl: '/assets/javascripts',
  paths: {
    lib: '/assets/lib'
  }
});

requirejs(['game/Solitaire'], function(Solitaire) {
  'use strict';

  if(window.PhaserGlobal === undefined) {
    window.PhaserGlobal = {};
  }
  window.PhaserGlobal.hideBanner = true;

  window.onerror = function(message, url, line, col, error) {
    var deviceId = '00000000-0000-0000-0000-000000000000';
    if(localStorage !== undefined && localStorage !== null && localStorage.getItem('device') !== null) {
      deviceId = localStorage.getItem('device');
    }
    var e = {
      'device': deviceId,
      'message': message,
      'url': url,
      'line': line,
      'col': col,
      'stack': error.stack
    };
    if(window.solitaire !== undefined && window.solitaire.game !== undefined && window.solitaire.game.offlineService !== undefined) {
      window.solitaire.game.offlineService.jsError(e);
    } else {
      console.log('Unable to process error.');
    }

    return true;
  };

  window.solitaire = new Solitaire();
});
