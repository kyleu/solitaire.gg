define(["game/Rank", "game/Suit"], function (Rank, Suit) {
  "use strict";

  function Card(game, id, rank, suit, x, y) {
    this.id = id;
    this.rank = Rank.fromChar(rank);
    this.suit = Suit.fromChar(suit);

    var spriteIndex = (this.suit.index * 13) + (this.rank.value - 2);

    Phaser.Sprite.call(this, game, x, y, 'card-medium', spriteIndex);
  }

  Card.prototype = Object.create(Phaser.Sprite.prototype);
  Card.prototype.constructor = Card;

  Card.prototype.enableDragDrop = function() {
    this.inputEnabled = true;
    this.input.enableDrag(false, true);
    this.input.useHandCursor = true;
    this.events.onDragStart.add(this.onDragStart, this);
    this.events.onDragStop.add(this.onDragStop, this);
  };

  Card.prototype.onDragStart = function(card, pointer) {
    console.log("onDragStart", card, pointer);
  };

  Card.prototype.onDragStop = function(card, pointer) {
    console.log("onDragStop", card, pointer);
  };

  return Card;
});
