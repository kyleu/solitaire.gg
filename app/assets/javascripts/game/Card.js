define(["game/Rank", "game/Suit"], function (Rank, Suit) {
  "use strict";

  function Card(game, id, rank, suit) {
    this.id = id;
    this.rank = Rank.fromChar(rank);
    this.suit = Suit.fromChar(suit);
    this.animation = null;

    var spriteIndex = (this.suit.index * 13) + (this.rank.value - 2);
    Phaser.Sprite.call(this, game, 0, 0, 'card-medium', spriteIndex);

    this.game.cards[id] = this;

    this.game.physics.arcade.enable(this);
  }

  Card.prototype = Object.create(Phaser.Sprite.prototype);
  Card.prototype.constructor = Card;

  Card.prototype.update = function() {
    if(this.animation === null) {

    } else if(this.animation.id === "mouse") {
      if(this.game.physics.arcade.distanceToPointer(this, this.game.input.activePointer) > 20) {
        this.game.physics.arcade.moveToPointer(this, this.animation.speed);
      } else {
        this.body.velocity.set(0);
      }
    }
  };

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
