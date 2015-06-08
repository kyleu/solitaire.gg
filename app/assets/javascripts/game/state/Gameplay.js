define([
  'utils/Config', 'game/Rank', 'game/Suit', 'game/Card', 'game/pile/Pile',
  'game/helpers/Display', 'game/helpers/Keyboard', 'game/helpers/Tweens',
  'game/Playmat', 'game/state/GameState', 'game/helpers/GameplayHelper'
], function (cfg, Rank, Suit, Card, Pile, Display, Keyboard, Tweens, Playmat, GameState, GameplayHelper) {
  "use strict";

  function Gameplay(game) {
    GameState.call(this, 'gameplay', game);
    this.helper = new GameplayHelper(game);
  }

  Gameplay.prototype = Object.create(GameState.prototype);
  Gameplay.prototype.constructor = Gameplay;

  Gameplay.prototype.preload = function() {
    this.helper.load();
  };

  Gameplay.prototype.create = function() {
    GameState.prototype.create.apply(this, arguments);

    this.game.physics.startSystem(Phaser.Physics.ARCADE);
    Display.init(this.game);
    Keyboard.init(this.game);

    //this.bg = new Phaser.TileSprite(this, 0, 0, 0, 0, 'bg-texture');
    //this.bg.height = this.game.height * 2;
    //this.bg.width = this.game.width * 2;
    //this.bg.scale = { x: 0.5, y: 0.5 };
    //this.add.existing(this.bg);

    this.game.send("Ping", { timestamp: new Date().getTime() });
    this.game.time.events.loop(Phaser.Timer.SECOND * 10, function() {
      this.game.send("Ping", { timestamp: new Date().getTime() });
    }, this);

    this.helper.sendInitialMessage();
  };

  Gameplay.prototype.onMessage = function(c, v) {
    switch(c) {
      case "GameJoined":
        this.game.playmat = new Playmat(this.game, v.state.pileSets, v.state.layout);
        this.game.id = v.state.gameId;
        this.game.rules = v.state.rules;
        this.game.seed = v.state.seed;
        this.game.possibleMoves = v.moves;
        this.helper.loadPileSets(v.state.pileSets);
        this.helper.loadCards(v.state.pileSets, v.state.deck.originalOrder);
        this.game.options.setGame(v.state);
        break;
      case "PossibleMoves":
        this.game.possibleMoves = v.moves;
        if(v.moves.length === 0) {
          alert("No more moves available.");
        }
        this.game.options.setUndosAvalable(v.undosAvailable);
        this.game.options.setRedosAvalable(v.redosAvailable);
        break;
      case "CardRevealed":
        var existing = this.game.cards[v.card.id];
        var wasFaceUp = existing.faceUp;
        existing.rank = Rank.fromChar(v.card.r);
        existing.suit = Suit.fromChar(v.card.s);
        if(v.card.u && !wasFaceUp) {
          existing.turnFaceUp();
        } else {
          console.warn("Reveal received for already revealed card [" + v.card.id + "].");
        }
        break;
      case "CardHidden":
        var hidden = this.game.cards[v.id];
        hidden.turnFaceDown();
        break;
      case "CardMoved":
        this.helper.moveCard(v.card, v.source, v.target, v.turn);
        break;
      case "CardsMoved":
        for(var cardIndex in v.cards) {
          this.helper.moveCard(v.cards[cardIndex], v.source, v.target, v.turn);
        }
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
      case "Reconnect":
        this.game.playmat.destroy();
        this.game.send("JoinGame", { "id": this.game.id });
        console.log("Reconnecting to game [" + this.game.id + "].");
        break;
      case "ServerError":
        console.error("Server error encountered.", v);
        break;
      default:
        GameState.prototype.onMessage.apply(this, arguments);
    }
  };

  Gameplay.prototype.resize = function() {
    if(this.lastSize === undefined || this.lastSize[0] !== this.game.width || this.lastSize[1] !== this.game.height) {
      GameState.prototype.resize.apply(this, arguments);

      this.lastSize = [this.game.width, this.game.height];

      if(this.game.playmat !== undefined) {
        this.game.playmat.resize();
      }

      //if(this.bg !== undefined) {
      //  this.bg.height = this.game.height * 2;
      //  this.bg.width = this.game.width * 2;
      //}
    }
  };

  return Gameplay;
});
