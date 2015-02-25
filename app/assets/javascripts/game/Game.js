define(['game/state/InitialState', 'game/CardSet'], function (InitialState, CardSet) {
  "use strict";

  function Game(id) {
    this.id = id;
    this.ws = null;
    this.cardSet = CardSet.medium;
    var initialState = new InitialState(this);
    Phaser.Game.call(this, '100%', '100%', Phaser.AUTO, 'game-container', initialState);

    this.piles = {};
    this.cards = {};

    console.log("Game [" + id + "] started.");
  }

  Game.prototype = Phaser.Game.prototype;
  Game.prototype.constructor = Game;

  Game.prototype.onMessage = function(c, v) {
    if(c != "Pong") {
      //console.log("Message [" + c + "] received with content [" + JSON.stringify(v) + "].");
    }
    switch(c) {
      case "Pong":
        this.statusPanel.setLatency(new Date().getTime() - v.timestamp);
        break;
      case "VersionResponse":
        this.statusPanel.setVersion(v.version);
        break;
      default:
        this.state.getCurrentState().onMessage(c, v);
    }
  };

  Game.prototype.cardSelected = function(card, pointer) {
    this.ws.send("SelectCard", { card: card.id, pile: card.pile.id, pileIndex: card.pileIndex });
  };

  Game.prototype.pileSelected = function(pile, pointer) {
    this.ws.send("SelectPile", { pile: pile.id } );
  };

  Game.prototype.addPile = function(p) {
    this.piles[p.id] = p;
  };

  Game.prototype.addCard = function(c) {
    this.cards[c.id] = c;
  };

  return Game;
});
