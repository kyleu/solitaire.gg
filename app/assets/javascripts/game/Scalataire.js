define(['utils/Config', 'utils/Websocket', 'game/Game'], function (cfg, Websocket, Game) {
  "use strict";

  function Scalataire(variant) {
    this.ws = new Websocket(cfg.wsUrl, this);
    this.game = null;
    this.variant = variant;
  }

  Scalataire.prototype.startGame = function(variant) {
    document.getElementById('game-container').innerHTML = "";
    this.game = new Game(variant, this.ws);
  };

  Scalataire.prototype.onConnect = function(url) {
    console.log(cfg.name + " connected.");
    if(this.variant !== undefined) {
      this.startGame(this.variant);
    } else {
      var ctx = this;
      document.getElementById('game-container').innerHTML = '<div style="text-align: center; margin-top: 100px;"><button onclick="window.scalataire.startGame(\'klondike\');">Click here to start a game.</button></div>';
    }
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
