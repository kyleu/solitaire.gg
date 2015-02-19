define(["Config", "Websocket", "game/component/StatusPanel", "game/scene/LoadingScreen"], function (cfg, Websocket, StatusPanel, LoadingScreen) {
  "use strict";

  function InitialState(game) {
    Phaser.State.call(this, game);
  }

  InitialState.prototype = Object.create(Phaser.State.prototype);
  InitialState.prototype.constructor = InitialState;

  InitialState.prototype.onConnect = function(url) {
    this.game.statusPanel.connected();
    this.game.state.start('loading-screen');
  };

  InitialState.prototype.create = function() {
    var text = "Connecting...";

    this.game.time.advancedTiming = true;

    this.game.add.text(this.game.world.centerX - 60, this.game.world.centerY - 60, text, { font: "18px Helvetica", fill: "#ffffff" });

    this.game.scale.scaleMode = Phaser.ScaleManager.RESIZE;
    if(typeof Phaser.Plugin.Debug == 'function') {
      this.game.add.plugin(Phaser.Plugin.Debug);
    }

    this.game.statusPanel = new StatusPanel(this.game);
    this.game.ws = new Websocket(cfg.wsUrl, this, this.game);
  };

  InitialState.prototype.preload = function() {
    var loadingScreen = new LoadingScreen(this.game);
    this.game.state.add('loading-screen', loadingScreen);
  };

  InitialState.prototype.onMessage = function(c) {
    console.warn("Unhandled message [" + c + "]: " + JSON.stringify(v));
  };

  InitialState.prototype.update = function() {
    this.game.statusPanel.setFps(this.game.time.fps);
  };

  return InitialState;
});
