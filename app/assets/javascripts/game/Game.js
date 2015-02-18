define(['Config', 'Websocket'], function (cfg, Websocket) {
  "use strict";

  function Game(id, initialState) {
    Phaser.Game.call(this, '100%', '100%', Phaser.AUTO, 'game-container', initialState);
    this.id = id;
    this.ws = null;
  }

  Game.prototype = Phaser.Game.prototype;
  Game.prototype.constructor = Game;

  Game.prototype.onMessage = function(c, v) {
    cfg.log.debug("Message [" + c + "] received with content [" + JSON.stringify(v) + "].");
    this.state.getCurrentState().onMessage(c, v);
  };

  Game.prototype.orientationChange = function(scale) {

  };

  return Game;
});
