define(['Config', 'game/Card', 'game/Pile', 'game/Playmat'], function (cfg, Card, Pile, Playmat) {
  "use strict";

  function Gameplay(game) {
    Phaser.State.call(this, game);
  }

  Gameplay.prototype = Object.create(Phaser.State.prototype);
  Gameplay.prototype.constructor = Gameplay;

  Gameplay.prototype.preload = function() {
    this.game.load.image('bg-texture', '/assets/images/game/bg.jpg');
    this.game.load.image('card-back-medium', '/assets/images/game/cards/medium/BACK.png');
    this.game.load.image('empty-pile-medium', '/assets/images/game/cards/medium/EMPTY.png');
    this.game.load.spritesheet('card-medium', 'assets/images/game/cards/medium/ALL.png', 200, 300);
  };

  Gameplay.prototype.create = function() {
    var victoriousCheatKey = this.game.input.keyboard.addKey(Phaser.Keyboard.V);
    victoriousCheatKey.onDown.add(this.victorious, this);

    var dragDropCheatKey = this.game.input.keyboard.addKey(Phaser.Keyboard.D);
    dragDropCheatKey.onDown.add(this.legacyDragDrop, this);

    this.bg = new Phaser.TileSprite(this, 0, 0, 0, 0, 'bg-texture');
    this.bg.height = this.game.height * 2;
    this.bg.width = this.game.width * 2;
    this.bg.scale = { x: 0.5, y: 0.5 };
    this.add.existing(this.bg);

    this.game.time.events.loop(Phaser.Timer.SECOND * 2, function() {
      this.ws.send("Ping", { timestamp: new Date().getTime() });
    }, this.game);

    this.game.ws.send("JoinGame", { "game": "klondike", name: "Kyle" });
  };

  Gameplay.prototype.victorious = function() {
    console.log("Victorious!");
    for(var cardIndex in this.game.cards) {
      this.game.cards[cardIndex].animation = {id: "mouse", speed: 200 + Math.floor(Math.random() * 300)};
    }
  };

  Gameplay.prototype.legacyDragDrop = function() {
    for(var cardIndex in this.game.cards) {
      this.game.cards[cardIndex].enableLegacyDragDrop();
    }
  };

  Gameplay.prototype.update = function() {
    this.game.statusPanel.setFps(this.game.time.fps);
  };

  Gameplay.prototype.onMessage = function(c, v) {
    switch(c) {
      case "GameJoined":
        this.game.playmat = new Playmat(this.game, v.state.layout);

        // add piles
        var pileObjs = [];
        for(var pileIndex in v.state.piles) {
          var pile = v.state.piles[pileIndex];
          var pileObj = new Pile(this.game, pile.id, pile.behavior);
          pileObjs[pile.id] = pileObj;
          this.game.playmat.addPile(pileObj);
        }
        // add cards after
        for(var pileCardsIndex in v.state.piles) {
          var pileCards = v.state.piles[pileCardsIndex];

          for(var cardIndex in pileCards.cards) {
            var card = pileCards.cards[cardIndex];
            var cardObj = new Card(this.game, card.id, card.r, card.s, card.u);
            pileObjs[pileCards.id].addCard(cardObj);
          }
        }
        break;
      default:
        console.warn("Unhandled message [" + c + "]: " + JSON.stringify(v));
    }
  };

  Gameplay.prototype.resize = function(w, h) {
    if(this.game.playmat !== undefined) {
      this.game.playmat.resize();
    }

    this.bg.height = this.game.height * 2;
    this.bg.width = this.game.width * 2;
  };

  return Gameplay;
});
