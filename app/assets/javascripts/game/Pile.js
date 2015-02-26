define(['game/PileBehavior', 'game/PileDragDrop'], function (PileBehavior, PileDragDrop) {
  "use strict";

  function Pile(game, id, behavior) {
    Phaser.Group.call(this, game, game.playmat, id);
    this.id = id;
    this.behavior = PileBehavior[behavior];
    this.dragDrop = PileDragDrop[behavior];
    this.cards = [];
    this.game.addPile(this);

    this.empty = new Phaser.Sprite(game, 0, 0, 'empty-pile');
    this.empty.inputEnabled = true;
    this.empty.events.onInputUp.add(this.pileSelected, this);
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

    this.behavior.cardAdded(this, card);

    this.game.playmat.add(card);
  };

  Pile.prototype.removeCard = function(card) {
    var index = this.cards.indexOf(card);
    if(card.pile !== this || index === -1) {
      throw "Provided card is not a part of this pile.";
    }

    card.pile = null;
    card.pileIndex = 0;
    this.cards.splice(index, 1);

    for(var cardIndex in this.cards) {
      this.cards[cardIndex].pileIndex = parseInt(cardIndex);
    }

    this.behavior.cardRemoved(this, card);
  };

  Pile.prototype.startDrag = function(card, pointer) {
    this.dragDrop.startDrag(this, card, pointer);
  };

  Pile.prototype.endDrag = function() {
    this.dragDrop.endDrag(this);
  };

  Pile.prototype.cardSelected = function(c) {
    this.game.cardSelected(c);
  };

  Pile.prototype.pileSelected = function(pile, p) {
    this.game.pileSelected(this, p);
  };

  return Pile;
});
