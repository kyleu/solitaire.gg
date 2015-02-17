define(function () {
  "use strict";

  function Game(id) {
    this.id = id;
  }

  Game.prototype.start = function() {
    document.getElementById("loading-screen").style.display = "none";
    this.phaser = new Phaser.Game("100%", "100%", Phaser.AUTO, "game-container");
  };

  Game.prototype.orientationChange = function(scale) {

  };

  return Game;
});
