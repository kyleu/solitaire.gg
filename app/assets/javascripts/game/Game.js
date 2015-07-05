/* global define:false */
/* global Phaser:false */
/* global _:false */
/* global Solitaire:false */
define(['utils/Config', 'ui/Options', 'game/helpers/GameNetwork', 'game/state/InitialState', 'game/Help', 'game/Sandbox'],
function (config, Options, GameNetwork, InitialState, Help, Sandbox) {
  'use strict';

  function Game(ws) {
    this.id = null;
    this.cardSet = {
      cardWidth: 400,
      cardHeight: 600,
      cardHorizontalOffset: 80,
      cardVerticalOffset: 120
    };
    var initialState = new InitialState(this);
    var transparent = true;
    this.initialized = false;
    this.possibleMoves = [];
    this.movesMade = 0;

    var configOptions = {
      width: '100%',
      height: '100%',
      renderer: Phaser.AUTO,
      parent: 'game-container',
      state: initialState,
      transparent: transparent,
      resolution: 2
    };

    Phaser.Game.call(this, configOptions);

    this.options = new Options(this);
    this.status = {};
    this.piles = {};
    this.cards = {};

    this.help = new Help(this);

    GameNetwork.setGame(this);

    if(config.offline) {
      var self = this;
      var callback = function(json) {
        var ret = JSON.parse(json);
        self.onMessage(ret.c, ret.v);
      };

      this.offlineService = new Solitaire();
      this.offlineService.register(callback);
    } else {
      this.ws = ws;
    }
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

  return Game;
});
