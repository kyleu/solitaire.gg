define(['utils/Config', 'utils/Websocket', 'game/Game'], function (cfg, Websocket, Game) {
  "use strict";

  function Scalataire() {
    this.variant = 'klondike';
    this.ws = new Websocket(cfg.wsUrl, this);
    this.game = null;
    if(cfg.autoStart) {
      this.startGame();
    } else {
      document.getElementById('game-container').innerHTML = '<div style="text-align: center; margin-top: 100px;"><button onclick="window.scalataire.startGame();">Click here to start a game.</button></div>';
    }
  }

  Scalataire.prototype.startGame = function() {
    document.getElementById('game-container').innerHTML = "";
    this.game = new Game(this.variant, this.ws);
  };

  Scalataire.prototype.onConnect = function(url) {
    console.log(cfg.name + " connected.");
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
