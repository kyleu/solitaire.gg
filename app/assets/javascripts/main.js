requirejs.config({
  baseUrl: '/assets/javascripts',
  paths: {
    lib: '/assets/lib'
  }
});

requirejs(['game/Scalataire'], function(Scalataire) {
  window.scalataire = new Scalataire('klondike');
});
