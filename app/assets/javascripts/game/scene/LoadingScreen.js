define(['Config', 'game/scene/Sandbox'], function (cfg, Sandbox) {
  "use strict";

  function LoadingScreen(game) {
    Phaser.State.call(this, game);
  }

  LoadingScreen.prototype = Object.create(Phaser.State.prototype);
  LoadingScreen.prototype.constructor = LoadingScreen;

  LoadingScreen.prototype.preload = function() {
    var sandbox = new Sandbox(this.game);
    this.game.state.add('sandbox', sandbox);
  };

  LoadingScreen.prototype.create = function() {
    this.message = this.game.add.text(this.game.world.centerX - 90, this.game.world.centerY - 60, "Loading Scalataire...", { font: "18px Helvetica", fill: "#ffffff"});
    this.game.ws.send("Ping", { "timestamp": new Date().getTime() });
  };

  LoadingScreen.prototype.onMessage = function(c, v) {
    switch(c) {
      case "Pong":
        console.info("Message [Pong] received in [" + (new Date().getTime() - v.timestamp) + "ms].");
        this.game.state.start('sandbox');
        break;
      default:
        console.warn("Unhandled message [" + c + "]: " + JSON.stringify(v));
    }
  };

  LoadingScreen.prototype.resize = function(h, w) {
    this.message.x = this.game.world.centerX - 90;
    this.message.y = this.game.world.centerY - 60;
  };

  return LoadingScreen;
});
