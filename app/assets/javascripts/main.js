requirejs.config({
  baseUrl: '/assets/javascripts',
  paths: {
    lib: '/assets/lib'
  }
});

requirejs(['game/Solitaire'], function(Solitaire) {
  "use strict";

  window.solitaire = new Solitaire();
});
