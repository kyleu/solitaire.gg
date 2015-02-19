define(["Config", "Websocket", "game/component/StatusPanel", "game/state/LoadingScreen"], function (cfg, Websocket, StatusPanel, LoadingScreen) {
  "use strict";

  function InitialState(game) {
    Phaser.State.call(this, game);
  }

  InitialState.prototype = Object.create(Phaser.State.prototype);
  InitialState.prototype.constructor = InitialState;

  InitialState.prototype.onConnect = function(url) {
    this.game.statusPanel.connected();

    this.game.ws.send("GetVersion", { "timestamp": new Date().getTime() });
    this.game.ws.send("Ping", { timestamp: new Date().getTime() });

    this.game.state.start('loading');
  };

  InitialState.prototype.create = function() {
    var loadingScreen = new LoadingScreen(this.game);
    this.game.state.add('loading', loadingScreen);

    this.game.time.advancedTiming = true;

    this.game.scale.scaleMode = Phaser.ScaleManager.RESIZE;
    if(typeof Phaser.Plugin.Debug == 'function') {
      this.game.add.plugin(Phaser.Plugin.Debug);
    }

    this.game.statusPanel = new StatusPanel(this.game);
    this.game.ws = new Websocket(cfg.wsUrl, this, this.game);
  };

  InitialState.prototype.update = function() {
    this.game.statusPanel.setFps(this.game.time.fps);
  };

  InitialState.prototype.onMessage = function(c) {
    console.warn("Unhandled message [" + c + "]: " + JSON.stringify(v));
  };

  return InitialState;
});
