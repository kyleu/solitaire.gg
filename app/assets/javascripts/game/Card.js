define(["game/Rank", "game/Suit"], function (Rank, Suit) {
  "use strict";

  function Card(game, id, rank, suit, x, y) {
    this.id = id;
    this.rank = Rank.fromChar(rank);
    this.suit = Suit.fromChar(suit);

    var spriteIndex = (this.suit.index * 13) + (this.rank.value - 2);

    Phaser.Sprite.call(this, game, x, y, 'card-medium', spriteIndex);

    this.game.add.existing(this);
    this.anchor.set(0.5);
    this.game.physics.arcade.enable(this);
  }

  Card.prototype = Object.create(Phaser.Sprite.prototype);
  Card.prototype.constructor = Card;

  return Card;
});
