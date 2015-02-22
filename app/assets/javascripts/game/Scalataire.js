define(['Config', 'game/Game'], function (cfg, Game) {
  "use strict";

  function Scalataire(id) {
    document.getElementById('loading-screen').style.display = 'none';
    Game.call(this, id);
  }

  Scalataire.prototype = Game.prototype;
  Scalataire.prototype.constructor = Scalataire;

  return Scalataire;
});
