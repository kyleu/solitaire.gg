define(['Config', 'game/state/Gameplay', 'game/state/Sandbox'], function (cfg, Gameplay, Sandbox) {
  "use strict";

  function LoadingScreen(game) {
    Phaser.State.call(this, game);
  }

  LoadingScreen.prototype = Object.create(Phaser.State.prototype);
  LoadingScreen.prototype.constructor = LoadingScreen;

  LoadingScreen.prototype.preload = function() {
    var gameplay = new Gameplay(this.game);
    this.game.state.add('gameplay', gameplay);

    var sandbox = new Sandbox(this.game);
    this.game.state.add('sandbox', sandbox);
  };

  LoadingScreen.prototype.create = function() {
    this.message = this.game.add.text(this.game.world.centerX - 90, this.game.world.centerY - 60, "Loading Scalataire...", { font: "18px Helvetica", fill: "#ffffff"});

    this.game.state.start('gameplay');
    //this.game.state.start('sandbox');
  };

  LoadingScreen.prototype.onMessage = function(c, v) {
    switch(c) {
      default:
        console.warn("Unhandled message [" + c + "]: " + JSON.stringify(v));
    }
  };

  LoadingScreen.prototype.update = function () {
    this.game.statusPanel.setFps(this.game.time.fps);
  };

  LoadingScreen.prototype.resize = function() {
    this.message.x = this.game.world.centerX - 90;
    this.message.y = this.game.world.centerY - 60;
  };

  return LoadingScreen;
});
