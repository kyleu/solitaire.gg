define(['utils/Config', 'game/Game'], function (cfg, Game) {
  "use strict";

  function construct(that) {
    document.getElementById('game-container').innerHTML = "";
    Game.call(that, that.id);
  }

  function Scalataire(id) {
    this.id = id;
    if(cfg.autoStart) {
      construct(this);
    } else {
      document.getElementById('game-container').innerHTML = '<div style="text-align: center; margin-top: 100px;"><button onclick="window.game.startGame();">Click here to start a game.</button></div>';
    }
  }

  Scalataire.prototype = Game.prototype;
  Scalataire.prototype.constructor = Scalataire;

  Scalataire.prototype.startGame = function() {
    construct(this);
  };

  return Scalataire;
});
