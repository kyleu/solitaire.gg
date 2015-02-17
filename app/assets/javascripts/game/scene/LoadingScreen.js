define(["Config"], function (cfg) {
  "use strict";

  function LoadingScreen(game) {
    Phaser.State.call(this, game);
  }

  LoadingScreen.prototype = Object.create(Phaser.State.prototype);
  LoadingScreen.prototype.constructor = LoadingScreen;

  LoadingScreen.prototype.create = function() {
    //this.game.ws.send("Ping", {"timestamp": new Date().getTime()});
  };

  LoadingScreen.prototype.update = function() {
  };

  return LoadingScreen;
});
