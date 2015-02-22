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

    this.cards.push(card);
    this.add(card);

    if(this.behavior == "stock") {
      card.y = 0;
    } else {
      card.y = this.cards.length * 45;
    }

    this.empty.visible = false;
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

  return Pile;
});
