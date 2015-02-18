define(["Config"], function (cfg) {
  "use strict";

  function LoadingScreen(game) {
    Phaser.State.call(this, game);
  }

  LoadingScreen.prototype = Object.create(Phaser.State.prototype);
  LoadingScreen.prototype.constructor = LoadingScreen;

  LoadingScreen.prototype.create = function() {
    this.message = this.game.add.text(this.game.world.centerX - 90, this.game.world.centerY - 60, "Loading Scalataire...", { font: "18px Helvetica", fill: "#ffffff"});
    this.game.ws.send("Ping", { "timestamp": new Date().getTime() });
  };

  LoadingScreen.prototype.preload = function() {
  };

  LoadingScreen.prototype.onMessage = function(c, v) {
    switch(c) {
      case "Pong":
        cfg.log.info("Message [Pong] received in [" + (new Date().getTime() - v.timestamp) + "ms].");
        break;
      default:
        cfg.log.warn("Unhandled message [" + c + "]: " + JSON.stringify(v));
    }
  };

  LoadingScreen.prototype.resize = function(h, w) {
    this.message.x = this.game.world.centerX - 90;
    this.message.y = this.game.world.centerY - 60;
  };

  return LoadingScreen;
});
