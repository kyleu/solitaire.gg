/* global define:false */
/* global Phaser:false */
/* global _:false */
define(['utils/Config', 'game/Playmat', 'game/helpers/GameInit', 'game/helpers/GameNetwork', 'sandbox/Sandbox'],
function (config, Playmat, gameInit, GameNetwork, Sandbox) {
  'use strict';

  function Game(ws) {
    gameInit(this, ws);
  }

  Game.prototype = Phaser.Game.prototype;
  Game.prototype.constructor = Game;

  Game.prototype.cardSelected = function(card) { this.send('SelectCard', { card: card.id, pile: card.pile.id }); };
  Game.prototype.pileSelected = function(pile) { this.send('SelectPile', { pile: pile.id } ); };

  Game.prototype.addCard = function(c) { this.cards[c.id] = c; };
  Game.prototype.addPile = function(p) { this.piles[p.id] = p; };

  Game.prototype.sandbox = function() { return Sandbox.go(this); };

  Game.prototype.onMessage = GameNetwork.onMessage;
  Game.prototype.autoMove = GameNetwork.autoMove;
  Game.prototype.sendMove = GameNetwork.sendMove;
  Game.prototype.send = GameNetwork.send;

  Game.prototype.join = function(state, elapsedMs, moves) {
    this.playmat = new Playmat(this, state.pileSets, state.layout);
    this.id = state.gameId;
    this.rules = state.rules;
    this.seed = state.seed;
    this.possibleMoves = moves;
    this.options.setGame(state);
    if(elapsedMs > 0) {
      this.timer.start(elapsedMs);
    }
  };

  Game.prototype.initialMovesComplete = function() {
    _.each(this.piles, function(pile) {
      _.each(pile.cards, function(card) {
        card.bringToTop();
      });
    });

    var piles = this.piles;
    _.each(this.playmat.pileSets, function(pileSet) {
      pileSet.piles = _.map(pileSet.piles, function(p) { return piles[p.id]; });
    });

    this.initialized = true;
  };

  Game.prototype.redeal = function() {
    var tween = this.add.tween(this.playmat).to({ alpha: 0 }, 500, Phaser.Easing.Cubic.Out);
    tween.onComplete.add(function() {
      this.send('StartGame', { rules: config.rules });
    }, this);
    tween.start();
  };

  Game.prototype.refreshTextures = function() {
    _.each(this.piles, function(pile) {
      _.each(pile.cards, function(card) {
        card.updateSprite(card.faceUp);
      });
    });
  };

  return Game;
});
