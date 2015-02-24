define(["game/Card"], function (Card) {
  "use strict";

  function Pile(game, id, behavior) {
    Phaser.Group.call(this, game, game.playmat, id);
    this.id = id;
    this.behavior = behavior;
    this.cards = [];
    this.game.addPile(this);

    this.empty = new Phaser.Sprite(game, 0, 0, 'empty-pile-medium');
    this.add(this.empty);
  }

  Pile.prototype = Object.create(Phaser.Group.prototype);
  Pile.prototype.constructor = Pile;

  Pile.prototype.addCard = function(card) {
    card.x = 0;
    card.pile = this;
    card.pileIndex = this.cards.length;

    if(this.behavior == "stock") {
      card.y = this.y;
      card.x = this.x;
    } else {
      card.y = this.y + (this.cards.length * 45);
      card.x = this.x;
    }

    this.cards.push(card);
    this.game.playmat.add(card);
  };

  Pile.prototype.removeCard = function(c) {
    var index = this.cards.indexOf(c);
    if(card.pile !== this || index === -1) {
      throw "Provided card is not a part of this pile.";
    } else {
      this.cards.splice(index, 1);
      for(var cardIndex in this.cards) {
        this.cards[cardIndex].pileIndex = cardIndex;
      }
    }
    if(this.cards.length === 0) {
      this.empty.visible = true;
    }
  };

  Pile.prototype.startDrag = function(card, p) {
    if(this.behavior == "stock") {
      // No op
    } else {
      this.dragCards = this.cards.slice(card.pileIndex);
      for(var c in this.dragCards) {
        this.dragCards[c].startDrag(p, 0);
      }
    }
  };

  Pile.prototype.endDrag = function(p) {
    for(var c in this.dragCards) {
      this.dragCards[c].endDrag(p);
    }
    this.dragCards = null;
  };

  Pile.prototype.cardSelected = function(c, p) {
    this.game.cardSelected(c, p);
  };

  return Pile;
});
