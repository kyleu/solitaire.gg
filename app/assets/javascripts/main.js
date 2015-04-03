requirejs.config({
  baseUrl: '/assets/javascripts',
  paths: {
    lib: '/assets/lib'
  }
});

requirejs(['game/Scalataire'], function(Scalataire) {
  "use strict";

  window.scalataire = new Scalataire();
});
