define(['utils/Config', 'game/state/InitialState', 'game/CardSet', 'game/Help'], function (config, InitialState, CardSet, Help) {
  "use strict";

  function Game(ws) {
    this.id = null;
    var cardSize = "medium";
    this.cardSet = CardSet[config.cardSet][cardSize];
    var initialState = new InitialState(this);
    var transparent = true;
    Phaser.Game.call(this, '100%', '100%', Phaser.AUTO, 'game-container', initialState, transparent);
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

    console.log("Game started.");
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

  Game.prototype.addPile = function(p) {
    this.piles[p.id] = p;
  };

  Game.prototype.orderPiles = function() {
    _.each(this.piles, function(pile) {
      _.each(pile.cards, function(card) {
        card.bringToTop();
      });
    });
  };

  Game.prototype.addCard = function(c) {
    this.cards[c.id] = c;
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
