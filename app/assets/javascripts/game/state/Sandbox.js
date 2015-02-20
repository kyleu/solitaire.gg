define(['Config', 'game/Card', 'game/Pile', 'game/Playmat'], function (cfg, Card, Pile, Playmat) {
  "use strict";

  function Sandbox(game) {
    Phaser.State.call(this, game);
  }

  Sandbox.prototype = Object.create(Phaser.State.prototype);
  Sandbox.prototype.constructor = Sandbox;

  Sandbox.prototype.preload = function() {
    this.game.load.image("bg-texture", "/assets/images/game/bg.jpg");
    this.game.load.image("card-back-medium", "/assets/images/game/cards/medium/BACK.png");
    this.game.load.image("empty-pile-medium", "/assets/images/game/cards/medium/EMPTY.png");
    this.game.load.image("bg-texture", "/assets/images/game/bg.jpg");
    this.game.load.spritesheet('card-medium', 'assets/images/game/cards/medium/ALL.png', 200, 300);
  };

  Sandbox.prototype.create = function() {
    this.bg = this.game.add.tileSprite(0, 0, this.game.width * 2, this.game.height * 2, "bg-texture");
    this.bg.scale = { x: 0.5, y: 0.5 };

    this.game.time.events.loop(Phaser.Timer.SECOND * 2, function() {
      this.ws.send("Ping", { timestamp: new Date().getTime() });
    }, this.game);

    this.game.physics.startSystem(Phaser.Physics.ARCADE);

    this.game.ws.send("JoinGame", { "game": "sandbox", "name": "Kyle" });
  };

  Sandbox.prototype.update = function() {
    this.game.statusPanel.setFps(this.game.time.fps);
  };

  Sandbox.prototype.onMessage = function(c, v) {
    switch(c) {
      case "GameJoined":
        this.game.playmat = new Playmat(this.game, v.state.layout);

        for(var pileIndex in v.state.piles) {
          var pile = v.state.piles[pileIndex];
          var pileObj = new Pile(this.game, pile.id);
          this.game.playmat.addPile(pileObj);

          for(var cardIndex in pile.cards) {
            var card = pile.cards[cardIndex];
            var cardObj = new Card(this.game, card.id, card.r, card.s);
            pileObj.addCard(cardObj);
          }
        }

        var g = this.game;
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