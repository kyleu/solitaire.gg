define(['Config', 'game/Card'], function (cfg, Card) {
  "use strict";

  function Sandbox(game) {
    Phaser.State.call(this, game);
  }

  Sandbox.prototype = Object.create(Phaser.State.prototype);
  Sandbox.prototype.constructor = Sandbox;

  Sandbox.prototype.preload = function() {
    this.game.physics.startSystem(Phaser.Physics.ARCADE);
    this.game.load.image("bg-texture", "/assets/images/game/bg.jpg");
    this.game.load.spritesheet('card-medium', 'assets/images/game/cards/medium/ALL.png', 233, 359);
  };

  Sandbox.prototype.create = function() {
    this.bg = this.game.add.tileSprite(0, 0, this.game.width * 2, this.game.height * 2, "bg-texture");
    this.bg.scale = { x: 0.5, y: 0.5 };

    this.game.ws.send("JoinGame", { "name": "Kyle" });
  };

  Sandbox.prototype.update = function() {
    this.game.statusPanel.setFps(this.game.time.fps);
  };

  Sandbox.prototype.onMessage = function(c, v) {
    switch(c) {
      case "GameJoined":
        this.cards = [];
        for(var cardIndex in v.deck.cards) {
          var card = v.deck.cards[cardIndex];
          this.cards[cardIndex] = new Card(cardIndex, card.id, card.r, card.s, this.game, this.game.world.centerX, this.game.world.centerY);
        }
        break;
      default:
        console.warn("Unhandled message [" + c + "]: " + JSON.stringify(v));
    }
  };

  Sandbox.prototype.resize = function(w, h) {
    console.info("Sandbox resize.");
    this.bg.height = h * 2;
    this.bg.width = w * 2;
  };

  return Sandbox;
});
