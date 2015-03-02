define(function () {
  "use strict";

  function Pile(game, id, behavior) {
    Phaser.Group.call(this, game, game.playmat, id);
    this.id = id;
    this.behavior = behavior;
    this.cards = [];
    this.game.addPile(this);

    this.empty = new Phaser.Sprite(game, 0, 0, 'empty-pile');
    this.empty.inputEnabled = true;
    this.empty.events.onInputUp.add(function() {
      if(this.canSelectPile()) {
        this.pileSelected();
      }
    }, this);
    this.add(this.empty);

    this.intersectWidth = this.empty.width;
    this.intersectHeight = this.empty.height;
  }

  Pile.prototype = Object.create(Phaser.Group.prototype);
  Pile.prototype.constructor = Pile;

  Pile.prototype.addCard = function(card, index) {
    card.pile = this;
    card.pileIndex = this.cards.length;
    this.cards.push(card);

    this.cardAdded(card);

    this.game.playmat.add(card);
  };

  Pile.prototype.removeCard = function(card) {
    if(card.pile !== this) {
      throw "Provided card is not a part of this pile.";
    }

    card.pile = null;
    card.pileIndex = 0;

    var index = this.cards.indexOf(card);
    this.cards.splice(index, 1);

    for(var cardIndex in this.cards) {
      this.cards[cardIndex].pileIndex = parseInt(cardIndex);
    }

    this.cardRemoved(this, card);
  };

  Pile.prototype.pileSelected = function(pile, p) {
    this.game.pileSelected(this, p);
  };

  Pile.prototype.cardSelected = function(card) {
    this.game.cardSelected(card);
  };

  return Pile;
});
