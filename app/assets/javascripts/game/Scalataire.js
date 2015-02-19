define(['Config', 'game/Game'], function (cfg, Game) {
  "use strict";

  function Scalataire() {
    document.getElementById('loading-screen').style.display = 'none';
    Game.call(this, 'scalataire');
  }

  Scalataire.prototype = Game.prototype;
  Scalataire.prototype.constructor = Scalataire;

  return Scalataire;
});
