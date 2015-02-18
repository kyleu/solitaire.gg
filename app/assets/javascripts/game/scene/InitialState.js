define(["Config", "Websocket", "game/scene/LoadingScreen"], function (cfg, Websocket, LoadingScreen) {
  "use strict";

  function InitialState(game) {
    Phaser.State.call(this, game);
  }

  InitialState.prototype = Object.create(Phaser.State.prototype);
  InitialState.prototype.constructor = InitialState;

  InitialState.prototype.onConnect = function(url) {
    this.connectionStatus.text = "Connected";
    this.game.state.start('loading-screen');
  };

  InitialState.prototype.create = function() {
    var text = "Connecting...";

    this.game.add.text(this.game.world.centerX - 60, this.game.world.centerY - 60, text, { font: "18px Helvetica", fill: "#ffffff" });

    this.connectionStatus = new Phaser.Text(this.game, 20, 20, "", { font: "12px Helvetica", fill: "#ffffff" });
    this.game.stage.addChild(this.connectionStatus);

    var g = this.game;
    this.game.scale.scaleMode = Phaser.ScaleManager.RESIZE;
    if(typeof Phaser.Plugin.Debug == 'function') {
      this.game.add.plugin(Phaser.Plugin.Debug);
    }

    var that = this;
    this.game.ws = new Websocket(cfg.wsUrl, function(url) { that.onConnect(url); }, function(c, v) { g.onMessage(c, v); });
  };

  InitialState.prototype.preload = function() {
    var loadingScreen = new LoadingScreen(this.game);
    this.game.state.add('loading-screen', loadingScreen);
  };

  InitialState.prototype.onMessage = function(c) {
    cfg.log.warn("Unhandled message [" + c + "]: " + JSON.stringify(v));
  };

  InitialState.prototype.update = function() {
  };

  return InitialState;
});
