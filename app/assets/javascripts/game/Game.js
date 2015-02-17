define(['Config', 'Websocket', 'game/scene/LoadingScreen'], function (cfg, Websocket, LoadingScreen) {
  "use strict";

  function Game(id) {
    Phaser.Game.call(this, '100%', '100%', Phaser.AUTO, 'game-container', this);
    this.id = id;
    this.ws = null;
  }

  Game.prototype = Phaser.Game.prototype;
  Game.prototype.constructor = Game;

  Game.prototype.onConnect = function(url) {
    cfg.log.info("Connection opened to [" + url + "].");
    this.state.start("loading-screen");
    this.game.ws.send("Ping", {"timestamp": new Date().getTime()});
  };

  Game.prototype.onMessage = function(c, v) {
    console.log("Message [" + c + "] received with content [" + JSON.stringify(v) + "].");
    if(c == "Pong") {
      console.log("Message [Pong] received in [" + (new Date().getTime() - v.timestamp) + "ms].");
    }
  };

  Game.prototype.preload = function() {

  };

  Game.prototype.create = function() {
    this.scale.scaleMode = Phaser.ScaleManager.RESIZE;
    if(typeof Phaser.Plugin.Debug == 'function') {
      this.add.plugin(Phaser.Plugin.Debug);
    }

    var g = this;
    this.ws = new Websocket(cfg.wsUrl, function(url) { g.onConnect(url); }, function(c, v) { g.onMessage(c, v); });
  };

  Game.prototype.render = function() {

  };

  Game.prototype.orientationChange = function(scale) {

  };

  return Game;
});
