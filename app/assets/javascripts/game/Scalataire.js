define(['Config', 'game/Game', 'game/scene/InitialState'], function (cfg, Game, InitialState) {
  "use strict";

  function Scalataire() {
    document.getElementById('loading-screen').style.display = 'none';
    Game.call(this, 'scalataire', new InitialState(this));
  }

  Scalataire.prototype = Game.prototype;
  Scalataire.prototype.constructor = Scalataire;

  return Scalataire;
});
