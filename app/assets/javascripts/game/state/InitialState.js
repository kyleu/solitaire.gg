define(["Config", "Websocket", "game/component/StatusPanel", "game/state/GameState", "game/state/LoadingScreen"], function (cfg, Websocket, StatusPanel, GameState, LoadingScreen) {
  "use strict";

  function InitialState(game) {
    GameState.call(this, game);
  }

  InitialState.prototype = Object.create(GameState.prototype);
  InitialState.prototype.constructor = InitialState;

  InitialState.prototype.onConnect = function(url) {
    this.game.statusPanel.connected();

    this.game.ws.send("GetVersion", { "timestamp": new Date().getTime() });
    this.game.ws.send("Ping", { timestamp: new Date().getTime() });

    this.game.state.start('loading');
  };

  InitialState.prototype.create = function() {
    GameState.prototype.create.apply(this, arguments);

    var loadingScreen = new LoadingScreen(this.game);
    this.game.state.add('loading', loadingScreen);

    this.game.time.advancedTiming = true;

    this.game.scale.scaleMode = Phaser.ScaleManager.RESIZE;
    if(typeof Phaser.Plugin.Debug === 'function') {
      this.game.add.plugin(Phaser.Plugin.Debug);
    }

    this.game.statusPanel = new StatusPanel(this.game);
    this.game.ws = new Websocket(cfg.wsUrl, this, this.game);
  };

  return InitialState;
});
