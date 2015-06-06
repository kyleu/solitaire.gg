requirejs.config({
  baseUrl: '/assets/javascripts',
  paths: {
    lib: '/assets/lib'
  }
});

requirejs(['game/Solitaire'], function(Solitaire) {
  "use strict";

  window.PhaserGlobal.hideBanner = true;

  window.solitaire = new Solitaire();
});
