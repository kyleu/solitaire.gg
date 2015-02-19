define(["game/Card"], function (Card) {
  "use strict";

  function Pile(game, id, x, y) {
    Phaser.Group.call(this, game, game.world, id);
    this.id = id;
    this.x = x;
    this.y = y;

    this.add(new Phaser.Sprite(game, 0, 0, 'empty-pile-medium'));
  }

  Pile.prototype = Object.create(Phaser.Group.prototype);
  Pile.prototype.constructor = Card;

  return Pile;
});
