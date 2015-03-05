define([
  'Config', 'game/Card', 'game/pile/Pile',
  'game/pile/Stock', 'game/pile/Waste', 'game/pile/Foundation', 'game/pile/Tableau',
  'game/Playmat', 'game/state/GameState'
], function (cfg, Card, Pile, Stock, Waste, Foundation, Tableau, Playmat, GameState) {
  "use strict";

  function Gameplay(game) {
    GameState.call(this, game);
  }

  Gameplay.prototype = Object.create(GameState.prototype);
  Gameplay.prototype.constructor = Gameplay;

  Gameplay.prototype.preload = function() {
    var imageKey = this.game.cardSet.key;
    this.game.load.image('bg-texture', '/assets/images/game/bg.jpg');
    this.game.load.image('card-back', '/assets/images/game/cards/' + imageKey + '/BACK.png');
    this.game.load.image('empty-pile', '/assets/images/game/cards/' + imageKey + '/EMPTY.png');
    this.game.load.spritesheet('card', '/assets/images/game/cards/' + imageKey + '/ALL.png', this.game.cardSet.cardWidth, this.game.cardSet.cardHeight);
  };

  Gameplay.prototype.create = function() {
    GameState.prototype.create.apply(this, arguments);
    var victoriousCheatKey = this.game.input.keyboard.addKey(Phaser.Keyboard.V);
    victoriousCheatKey.onDown.add(this.victorious, this);

    this.bg = new Phaser.TileSprite(this, 0, 0, 0, 0, 'bg-texture');
    this.bg.height = this.game.height * 2;
    this.bg.width = this.game.width * 2;
    this.bg.scale = { x: 0.5, y: 0.5 };
    this.add.existing(this.bg);

    this.game.time.events.loop(Phaser.Timer.SECOND * 30, function() {
      this.ws.send("Ping", { timestamp: new Date().getTime() });
    }, this.game);

    this.game.scale.onOrientationChange.add(this.onOrientationChange, this);

    this.game.ws.send("JoinGame", { "game": "klondike" });
  };

  Gameplay.prototype.victorious = function() {
    console.log("Victorious!");
    for(var cardIndex in this.game.cards) {
      this.game.cards[cardIndex].animation = {id: "mouse", speed: 200 + Math.floor(Math.random() * 200)};
    }
  };

  Gameplay.prototype.onMessage = function(c, v) {
    switch(c) {
      case "GameJoined":
        this.game.playmat = new Playmat(this.game, v.state.layouts);
        this.loadPiles(v.state.piles);
        this.loadCards(v.state.piles);
        break;
      case "CardMoved":
        var movedCard = this.game.cards[v.card];
        var source = this.game.piles[v.source];
        var target = this.game.piles[v.target];

        if(v.turnFaceUp) {
          movedCard.turnFaceUp();
        } else if(v.turnFaceDown) {
          movedCard.turnFaceDown();
        }

        movedCard.bringToTop();
        source.removeCard(movedCard);
        target.addCard(movedCard);
        break;
      case "CardMoveCancelled":
        for(var cardCancelledIndex in v.cards) {
          this.game.cards[v.cards[cardCancelledIndex]].cancelDrag();
        }
        break;
      case "CardRevealed":
        var existing = this.game.cards[v.card.id];
        existing.updateSprite(v.card.r, v.card.s, v.card.u);
        if(v.card.u) {
          existing.turnFaceUp();
        }
        break;
      case "ServerError":
        console.error("Server error encountered.", v);
        break;
      default:
        GameState.prototype.onMessage.apply(this, arguments);
    }
  };

  Gameplay.prototype.loadPiles = function(piles) {
    for(var pileIndex in piles) {
      var pile = piles[pileIndex];
      var pileObj = null;
      switch(pile.behavior) {
        case "stock":
          pileObj = new Stock(this.game, pile.id, pile.behavior);
          break;
        case "waste":
          pileObj = new Waste(this.game, pile.id, pile.behavior);
          break;
        case "foundation":
          pileObj = new Foundation(this.game, pile.id, pile.behavior);
          break;
        case "tableau":
          pileObj = new Tableau(this.game, pile.id, pile.behavior);
          break;
        default:
          throw "Unknown pile behavior [" + pile.behavior + "].";
      }
      this.game.playmat.addPile(pileObj);
    }
  };

  Gameplay.prototype.loadCards = function(piles) {
    for(var pileIndex in piles) {
      var pile = piles[pileIndex];
      var pileObj = this.game.piles[pile.id];
      for(var cardIndex in pile.cards) {
        var card = pile.cards[cardIndex];
        var cardObj = new Card(this.game, card.id, card.r, card.s, card.u);
        pileObj.addCard(cardObj);
      }
    }
  };

  Gameplay.prototype.onOrientationChange = function (scale, prevOrientation) {
    alert("OC: Was [" + prevOrientation + "], now [" + scale.screenOrientation + "]. Window: [" + window.innerWidth + "x" +  window.innerHeight + "].");
    document.getElementById("game-container").style.width = windoe.innerWidth;
    document.getElementById("game-container").style.height = windoe.innerHeight;
  };

  Gameplay.prototype.resize = function(w, h) {
    GameState.prototype.resize.apply(this, arguments);

    if(this.game.playmat !== undefined) {
      this.game.playmat.resize();
    }

    this.bg.height = this.game.height * 2;
    this.bg.width = this.game.width * 2;
  };

  return Gameplay;
});
