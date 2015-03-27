define([
  'utils/Config', 'game/Rank', 'game/Suit', 'game/Card', 'game/pile/Pile', 'game/pile/PileHelpers',
  'game/helpers/Display', 'game/helpers/Keyboard', 'game/Playmat', 'game/state/GameState'
], function (cfg, Rank, Suit, Card, Pile, PileHelpers, Display, Keyboard, Playmat, GameState) {
  "use strict";

  function Gameplay(game) {
    GameState.call(this, 'gameplay', game);
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

    Display.init(this.game);
    Keyboard.init(this.game);

    this.bg = new Phaser.TileSprite(this, 0, 0, 0, 0, 'bg-texture');
    this.bg.height = this.game.height * 2;
    this.bg.width = this.game.width * 2;
    this.bg.scale = { x: 0.5, y: 0.5 };
    this.add.existing(this.bg);

    this.game.ws.send("Ping", { timestamp: new Date().getTime() });
    this.game.time.events.loop(Phaser.Timer.SECOND * 10, function() {
      this.game.ws.send("Ping", { timestamp: new Date().getTime() });
    }, this);

    if(cfg.initialAction === undefined) {
      cfg.initialAction = ["start"];
    }
    switch(cfg.initialAction[0]) {
      case "start":
        if(cfg.seed === undefined) {
          this.game.ws.send("StartGame", {"variant": cfg.variant});
        } else {
          this.game.ws.send("StartGame", {"variant": cfg.variant, "seed": cfg.seed});
        }
        break;
      case "join":
        this.game.ws.send("JoinGame", {"id": cfg.initialAction[1]});
        break;
      case "observe":
        if(cfg.initialAction.length === 2) {
          this.game.ws.send("ObserveGame", {"id": cfg.initialAction[1]});
        } else {
          this.game.ws.send("ObserveGame", {"id": cfg.initialAction[1], "as": cfg.initialAction[2]});
        }
        break;
      default:
        alert("Invalid initial action [" + cfg.initialAction + "].");
    }
  };

  Gameplay.prototype.onMessage = function(c, v) {
    switch(c) {
      case "GameJoined":
        this.game.playmat = new Playmat(this.game, v.state.layouts);
        this.game.id = v.state.gameId;
        this.game.variant = v.variant;
        this.game.seed = v.state.seed;
        this.game.possibleMoves = v.moves;
        this.loadPiles(v.state.piles);
        this.loadCards(v.state.piles);
        break;
      case "PossibleMoves":
        this.game.possibleMoves = v.moves;
        if(v.moves.length === 0) {
          alert("No more moves available.");
        }
        break;
      case "CardRevealed":
        var existing = this.game.cards[v.card.id];
        var wasFaceUp = existing.faceUp;
        existing.rank = Rank.fromChar(v.card.r);
        existing.suit = Suit.fromChar(v.card.s);
        if(v.card.u && !wasFaceUp) {
          existing.turnFaceUp();
        }
        break;
      case "CardRemoved":
        var removedCard = this.game.cards[v.card];
        removedCard.pile.removeCard(removedCard);
        PileHelpers.tweenRemove(removedCard);
        break;
      case "CardMoved":
        var movedCard = this.game.cards[v.card];
        var source = this.game.piles[v.source];
        var target = this.game.piles[v.target];

        if(v.turnFaceUp && !movedCard.faceUp) {
          movedCard.turnFaceUp();
        } else if(v.turnFaceDown && movedCard.faceUp) {
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
      case "GameLost":
        alert("You lose!");
        break;
      case "GameWon":
        alert("You win!");
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
      var pileObj = new Pile(this.game, pile.id, pile.behavior, pile.options);
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

  Gameplay.prototype.resize = function(w, h) {
    GameState.prototype.resize.apply(this, arguments);

    if(this.game.playmat !== undefined) {
      this.game.playmat.resize();
    }

    if(this.bg !== undefined) {
      this.bg.height = this.game.height * 2;
      this.bg.width = this.game.width * 2;
    }
  };

  return Gameplay;
});
