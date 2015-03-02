define(['Config', 'game/Card', 'game/pile/Pile', 'game/Playmat'], function (cfg, Card, Pile, Playmat) {
  "use strict";

  function Sandbox(game) {
    Phaser.State.call(this, game);
  }

  Sandbox.prototype = Object.create(Phaser.State.prototype);
  Sandbox.prototype.constructor = Sandbox;

  Sandbox.prototype.preload = function() {
    this.game.load.image("bg-texture", "/assets/images/game/bg.jpg");
    this.game.load.image("card-back", "/assets/images/game/cards/medium/BACK.png");
    this.game.load.image("empty-pile", "/assets/images/game/cards/medium/EMPTY.png");
    this.game.load.spritesheet('card', '/assets/images/game/cards/medium/ALL.png', this.game.cardSet.cardWidth, this.game.cardSet.cardHeight);
  };

  Sandbox.prototype.create = function() {
    GameState.prototype.create.apply(this, arguments);

    this.bg = new Phaser.TileSprite(this, 0, 0, 0, 0, 'bg-texture');
    this.bg.height = this.game.height * 2;
    this.bg.width = this.game.width * 2;
    this.bg.scale = { x: 0.5, y: 0.5 };
    this.add.existing(this.bg);

    this.game.time.events.loop(Phaser.Timer.SECOND * 2, function() {
      this.ws.send("Ping", { timestamp: new Date().getTime() });
    }, this.game);

    this.game.ws.send("JoinGame", { "game": "sandbox", "name": "Kyle" });
  };

  Sandbox.prototype.onMessage = function(c, v) {
    switch(c) {
      case "GameJoined":
        this.game.playmat = new Playmat(this.game, v.state.layouts);

        for(var pileIndex in v.state.piles) {
          var pile = v.state.piles[pileIndex];
          var pileObj = new Pile(this.game, pile.id);
          this.game.playmat.addPile(pileObj);

          for(var cardIndex in pile.cards) {
            var card = pile.cards[cardIndex];
            var cardObj = new Card(this.game, card.id, card.r, card.s, card.u);
            pileObj.addCard(cardObj);
          }
        }
        break;
      default:
        GameState.prototype.onMessage.apply(this, arguments);
    }
  };

  Sandbox.prototype.resize = function(w, h) {
    GameState.prototype.resize.apply(this, arguments);

    if(this.game.playmat !== undefined) {
      this.game.playmat.resize();
    }
  };

  return Sandbox;
});
