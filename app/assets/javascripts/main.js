/* global requirejs:false */

requirejs.config({
  baseUrl: '/assets/javascripts',
  paths: {
    lib: '/assets/lib'
  }
});

requirejs(['game/Solitaire'], function(Solitaire) {
  'use strict';

  let x = 1;
  const y = 2;
  console.log(x + y);

  if(window.PhaserGlobal === undefined) {
    window.PhaserGlobal = {};
  }
  window.PhaserGlobal.hideBanner = true;

  window.solitaire = new Solitaire();
});
