define(['utils/Config', 'ui/Options', 'game/state/InitialState', 'game/CardSet', 'game/Help'], function (config, Options, InitialState, CardSet, Help) {
  "use strict";

  function Game(ws) {
    this.id = null;
    var cardSize = "medium";
    this.cardSet = CardSet[config.cardSet][cardSize];
    var initialState = new InitialState(this);
    var transparent = true;
    this.initialized = false;

    this.possibleMoves = [];
    this.recentMoves = [];

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

  Game.prototype.onMessage = function(c, v) {
    if(c != "Pong") {
      //console.log("Message [" + c + "] received with content [" + JSON.stringify(v) + "].");
    }
    switch(c) {
      case "Pong":
        this.status.latency = (new Date().getTime() - v.timestamp);
        break;
      case "VersionResponse":
        this.status.version = v.version;
        break;
      default:
        this.state.getCurrentState().onMessage(c, v);
    }
  };

  Game.prototype.cardSelected = function(card) {
    this.send("SelectCard", { card: card.id, pile: card.pile.id });
  };

  Game.prototype.pileSelected = function(pile) {
    this.send("SelectPile", { pile: pile.id } );
  };

  Game.prototype.addCard = function(c) {
    this.cards[c.id] = c;
  };

  Game.prototype.addPile = function(p) {
    this.piles[p.id] = p;
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

  Game.prototype.autoMove = function() {
    var idx = 0;
    var move = null;
    var candidate = null;
    var matches = function(e) { return _.isEqual(e, candidate); };
    while(move === null && this.possibleMoves.length > idx) {
      candidate = this.possibleMoves[idx];
      if(_.find(this.recentMoves, matches) !== undefined) {
        idx += 1;
      } else {
        move = candidate;
      }
    }
    if(move !== null) {
      if(move.moveType === 'move-cards') {
        this.recentMoves.push(move);
      }
      this.sendMove(move);
    }
  };

  Game.prototype.sendMove = function(move) {
    switch(move.moveType) {
      case "move-cards":
        this.send("MoveCards", {cards: move.cards, src: move.sourcePile, tgt: move.targetPile});
        break;
      case "select-card":
        var selectedCard = this.cards[move.cards[0]];
        this.cardSelected(selectedCard);
        break;
      case "select-pile":
        var selectedPile = this.piles[move.sourcePile];
        this.pileSelected(selectedPile);
        break;
      default:
        throw "Unknown move [" + move.moveType + "].";
    }
  };

  Game.prototype.send = function(c, v) {
    if(config.offline) {
      this.offlineService.receive(c, v);
    } else {
      this.ws.send(c, v);
    }
  };

  return Game;
});
