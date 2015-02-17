define(['Config', 'game/Game', 'game/scene/LoadingScreen'], function (cfg, Game, LoadingScreen) {
  "use strict";

  function Scalataire() {
    Game.call(this, 'scalataire');
  }

  Scalataire.prototype = Game.prototype;
  Scalataire.prototype.constructor = Scalataire;

  Scalataire.prototype.start = function() {
    document.getElementById('loading-screen').style.display = 'none';

    var loadingScreen = new LoadingScreen(this);
    this.state.add("loading-screen", loadingScreen);
  };

  return Scalataire;
});
