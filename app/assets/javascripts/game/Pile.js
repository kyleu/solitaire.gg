define(["game/Card"], function (Card) {
  "use strict";

  function Pile(game, id, x, y) {
    Phaser.Group.call(this, game, game.world, id);
    this.id = id;
    this.x = x;
    this.y = y;

    this.empty = new Phaser.Sprite(game, 0, 0, 'empty-pile-medium');

    this.cards = [];

    this.add(this.empty);
  }

  Pile.prototype = Object.create(Phaser.Group.prototype);
  Pile.prototype.constructor = Card;

  return Pile;
});
