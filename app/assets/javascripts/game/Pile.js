define(["game/Card"], function (Card) {
  "use strict";

  function Pile(game, id) {
    Phaser.Group.call(this, game, game.playmat, id);
    this.id = id;
    this.cards = [];
    this.game.piles[id] = this;

    this.empty = new Phaser.Sprite(game, 0, 0, 'empty-pile-medium');
    this.add(this.empty);
  }

  Pile.prototype = Object.create(Phaser.Group.prototype);
  Pile.prototype.constructor = Card;

  Pile.prototype.addCard = function(card) {
    card.x = this.x;
    card.y = this.y + this.cards.length * 45;
    this.cards.push(card);
    this.game.playmat.add(card);
  };

  return Pile;
});
