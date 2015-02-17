define(['Websocket'], function (Websocket) {
  "use strict";

  function onConnect(url) {
    Game.ws.send("Ping", {"timestamp": 0});
  }

  function onMessage(c, v) {
    console.log("Message [" + c + "] received with content [" + JSON.stringify(v) + "].");
  }

  var Game = {
    id: "scalataire",
    phaser: null,
    ws: null,

    start: function() {
      document.getElementById('loading-screen').style.display = 'none';
      Game.phaser = new Phaser.Game('100%', '100%', Phaser.AUTO, 'game-container', Game);
      Game.ws = new Websocket("ws://localhost:9000/websocket", onConnect, onMessage);
    },

    preload: function() {

    },
    create: function() {
      Game.phaser.scale.scaleMode = Phaser.ScaleManager.RESIZE;
      if(typeof Phaser.Plugin.Debug == 'function') {
        Game.phaser.add.plugin(Phaser.Plugin.Debug);
      }
    },
    update: function() {

    },
    render: function() {

    },
    orientationChange: function(scale) {

    }
  };


  return Game;
});
