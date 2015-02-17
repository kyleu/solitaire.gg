requirejs.config({
  baseUrl: 'assets/javascripts',
  paths: {
    lib: 'assets/lib'
  }
});

requirejs(['game/Game'], function(Game) {
  new Game().start();
});
