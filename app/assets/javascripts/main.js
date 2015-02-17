requirejs.config({
  baseUrl: 'assets/javascripts',
  paths: {
    lib: 'assets/lib'
  }
});

requirejs(['game/Scalataire'], function(Scalataire) {
  new Scalataire().start();
});
