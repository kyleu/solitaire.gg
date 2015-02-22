define(["game/Rank", "game/Suit"], function (Rank, Suit) {
  "use strict";

  function Card(game, id, rank, suit, faceUp) {
    this.id = id;
    this.rank = Rank.fromChar(rank);
    this.suit = Suit.fromChar(suit);
    this.name = rank + suit + ": " + id;
    this.faceUp = faceUp;

    this.animation = null;

    this.spriteIndex = (this.suit.index * 13) + (this.rank.value - 2);
    if(this.faceUp) {
      Phaser.Sprite.call(this, game, 0, 0, 'card-medium', this.spriteIndex);
    } else {
      Phaser.Sprite.call(this, game, 0, 0, 'card-back-medium');
    }

    this.inputEnabled = true;

    this.game.addCard(this);
    this.game.physics.arcade.enable(this);

    this.events.onInputDown.add(this.onInputDown);
  }

  Card.prototype = Object.create(Phaser.Sprite.prototype);
  Card.prototype.constructor = Card;

  Card.prototype.onInputDown = function(e, p) {
    e.game.cardSelected(e, p);
  };

  Card.prototype.toString = function() {
    return this.rank.char + this.suit.char + ": " + this.id.substring(0, 8);
  };

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

  Card.prototype.enableLegacyDragDrop = function() {
    this.input.enableDrag(false, true);
  };

  return Card;
});
