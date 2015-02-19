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
    console.log("Message [" + c + "] received with content [" + JSON.stringify(v) + "].");
    // Handle common messages
    switch(c) {
      case "Pong":
        this.statusPanel.setLatency(new Date().getTime() - v.timestamp);
        break;
      default:
    }
    // Prevent send if desired.
    switch(c) {
      default:
        this.state.getCurrentState().onMessage(c, v);
    }
  };

  return Game;
});
