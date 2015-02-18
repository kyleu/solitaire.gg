define(["Config"], function (cfg) {
  "use strict";

  function Card(index, id, rank, suit, game, x, y) {
    Phaser.Sprite.call(this, game, x, y, rank + suit);

    this.index = index;

    this.id = id;
    this.rank = rank;
    this.suit = suit;

    this.x = x;
    this.y = y;

    this.game.add.existing(this);
    this.anchor.set(0.5);
    this.game.physics.arcade.enable(this);
  }

  Card.prototype = Object.create(Phaser.Sprite.prototype);
  Card.prototype.constructor = Card;

  Card.prototype.update = function() {
    if(this.game.physics.arcade.distanceToPointer(this, this.game.input.activePointer) > 20) {
      this.game.physics.arcade.moveToPointer(this, 20 + (20 * this.index));
    } else {
      this.body.velocity.set(0);
    }
  };

  return Card;
});
