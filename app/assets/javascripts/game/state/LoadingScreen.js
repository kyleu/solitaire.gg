define(['utils/Config', 'game/state/Gameplay', 'game/state/GameState'], function (cfg, Gameplay, GameState) {
  "use strict";

  function LoadingScreen(game) {
    GameState.call(this, 'loading', game);
  }

  LoadingScreen.prototype = Object.create(GameState.prototype);
  LoadingScreen.prototype.constructor = LoadingScreen;

  LoadingScreen.prototype.preload = function() {
    var gameplay = new Gameplay(this.game);
    this.game.state.add('gameplay', gameplay);
  };

  LoadingScreen.prototype.create = function() {
    GameState.prototype.create.apply(this, arguments);

    this.message = this.game.add.text(this.game.world.centerX - 90, this.game.world.centerY - 60, "Loading " + cfg.name + "...", { font: "18px Helvetica", fill: "#ffffff"});
    this.game.state.start('gameplay');
  };

  LoadingScreen.prototype.resize = function() {
    GameState.prototype.resize.apply(this, arguments);

    this.message.x = this.game.world.centerX - 90;
    this.message.y = this.game.world.centerY - 60;
  };

  return LoadingScreen;
});
