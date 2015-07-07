/* global define:false */
/* global Phaser:false */
/* global _:false */
define([
  'utils/Config', 'game/Rank', 'game/Suit', 'game/card/Card', 'game/pile/Pile',
  'game/helpers/Backdrop', 'game/helpers/Display', 'game/helpers/Keyboard', 'game/helpers/Tweens',
  'game/Playmat', 'game/state/GameState', 'game/state/GameplayHelper'
], function (cfg, Rank, Suit, Card, Pile, Backdrop, Display, Keyboard, Tweens, Playmat, GameState, GameplayHelper) {
  'use strict';

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
    this.game.keyboard = new Keyboard(this.game);
    this.game.keyboard.init();

    this.backdrop = new Backdrop(this.game);
    this.add.existing(this.backdrop.bg);

    this.game.send('Ping', { timestamp: new Date().getTime() });
    this.game.time.events.loop(Phaser.Timer.SECOND * 10, function() {
      this.game.send('Ping', { timestamp: new Date().getTime() });
    }, this);

    this.helper.sendInitialMessage();
  };

  Gameplay.prototype.onMessage = function(c, v) {
    var self = this;
    switch(c) {
      case 'GameJoined':
        this.game.playmat = new Playmat(this.game, v.state.pileSets, v.state.layout);
        this.game.id = v.state.gameId;
        this.game.rules = v.state.rules;
        this.game.seed = v.state.seed;
        this.game.possibleMoves = v.moves;
        this.helper.loadCardImages();
        this.helper.loadPileSets(v.state.pileSets);
        this.helper.loadCards(v.state.pileSets, v.state.deck.originalOrder);
        this.game.options.setGame(v.state);
        break;
      case 'PossibleMoves':
        this.game.possibleMoves = v.moves;
        if(v.moves.length === 0) {
          alert('No more moves available.');
        }
        this.game.options.setUndosAvailable(v.undosAvailable);
        this.game.options.setRedosAvailable(v.redosAvailable);
        break;
      case 'CardRevealed':
        var existing = this.game.cards[v.card.id];
        var wasFaceUp = existing.faceUp;
        existing.rank = Rank.fromChar(v.card.r);
        existing.suit = Suit.fromChar(v.card.s);
        if(v.card.u && !wasFaceUp) {
          existing.turnFaceUp();
        } else {
          console.warn('Reveal received for already revealed card [' + v.card.id + '].');
        }
        break;
      case 'CardHidden':
        var hidden = this.game.cards[v.id];
        hidden.turnFaceDown();
        break;
      case 'CardMoved':
        this.helper.moveCard(v.card, v.source, v.target, v.turn);
        break;
      case 'CardsMoved':
        _.each(v.cards, function(movedCard) {
          self.helper.moveCard(movedCard, v.source, v.target, v.turn);
        });
        break;
      case 'CardMoveCancelled':
        _.each(v.cards, function(cancelledCard) {
          self.game.cards[cancelledCard].cancelDrag();
        });
        break;
      case 'GameLost':
        alert('You lose!');
        break;
      case 'GameWon':
        alert('You win!');
        break;
      case 'Reconnect':
        this.game.playmat.destroy();
        this.game.send('JoinGame', { 'id': this.game.id });
        console.log('Reconnecting to game [' + this.game.id + '].');
        break;
      case 'ServerError':
        console.error('Server error encountered.', v);
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

      if(this.backdrop !== undefined) {
        this.backdrop.height = this.game.height;
        this.backdrop.width = this.game.width;
      }
    }
  };

  return Gameplay;
});
