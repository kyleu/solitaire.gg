define(['utils/Config', 'utils/Websocket', 'game/Game'], function (cfg, Websocket, Game) {
  "use strict";

  function Scalataire(callback) {
    this.callback = callback;
    this.ws = new Websocket(cfg.wsUrl, this);
    this.game = null;
  }

  Scalataire.prototype.startGame = function(variant) {
    this.game = new Game(variant, this.ws);
  };

  Scalataire.prototype.observeGame = function(id, account) {
    console.log("Observing game [" + id + "] as [" + account + "].");
    this.game = new Game("klondike", this.ws);
  };

  Scalataire.prototype.onConnect = function(url) {
    console.log(cfg.name + " connected.");
    this.callback(this);
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
