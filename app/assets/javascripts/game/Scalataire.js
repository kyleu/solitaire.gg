define(['utils/Config', 'utils/Websocket', 'game/Game'], function (cfg, Websocket, Game) {
  "use strict";

  function Scalataire() {
    this.ws = new Websocket(cfg.wsUrl, this);
    this.game = null;
  }

  Scalataire.prototype.onConnect = function(url) {
    console.log(cfg.name + " connected.");
    this.game = new Game(this.ws);
  };

  Scalataire.prototype.onMessage = function(c, v) {
    if(this.game === null) {
      console.log("No game available for message [" + c + "].");
    } else {
      this.game.onMessage(c, v);
    }
  };

  return Scalataire;
});
