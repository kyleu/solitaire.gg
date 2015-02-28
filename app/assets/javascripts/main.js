requirejs.config({
  baseUrl: '/assets/javascripts',
  paths: {
    lib: '/assets/lib'
  }
});

requirejs(['game/Scalataire'], function(Scalataire) {
  window.game = new Scalataire('klondike');
});
