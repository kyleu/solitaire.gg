define(['utils/Config', 'utils/Websocket', 'game/Game'], function (cfg, Websocket, Game) {
  "use strict";

  function Solitaire() {
    if(cfg.offline) {
      this.game = new Game();
    } else {
      this.game = null;
      this.ws = new Websocket(cfg.wsUrl, this);
    }
  }

  Solitaire.prototype.onConnect = function() {
    console.log(cfg.account.name + " connected.");
    if(this.game === null) {
      this.game = new Game(this.ws);
    } else {
      this.game.onMessage("Reconnect", {});
    }
  };

  Solitaire.prototype.onMessage = function(c, v) {
    if(this.game === null) {
      console.log("No game available for message [" + c + "].");
    } else {
      this.game.onMessage(c, v);
    }
  };

  return Solitaire;
});
