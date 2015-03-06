requirejs.config({
  baseUrl: '/assets/javascripts',
  paths: {
    lib: '/assets/lib'
  }
});

requirejs(['Config', 'game/Options', 'game/Scalataire'], function(cfg, options, Scalataire) {
  options.start();
  if(cfg.autoStart) {
    window.game = new Scalataire('klondike');
  }
});
