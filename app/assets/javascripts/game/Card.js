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

    this.events.onInputDown.add(this.onInputDown, this);
    this.events.onInputUp.add(this.onInputUp, this);
  }

  Card.prototype = Object.create(Phaser.Sprite.prototype);
  Card.prototype.constructor = Card;

  Card.prototype.onInputDown = function(e, p) {
  };

  Card.prototype.onInputUp = function(e, p) {
    this.inputDown = false;
    var xDelta = Math.abs(p.positionDown.x - p.positionUp.x);
    var yDelta = Math.abs(p.positionDown.y - p.positionUp.y);
    if(xDelta > 5 || yDelta > 5) {
      // Dragged
    } else {
      this.pile.cardSelected(e, p);
    }
  };

  Card.prototype.toString = function() {
    return this.rank.char + this.suit.char + ": " + this.id.substring(0, 8);
  };

  Card.prototype.update = function() {
    if(this.animation === null) {

    } else if(this.animation.id === "mouse") {
      var newX = (this.x * this.game.playmat.scale.x) + this.game.playmat.x;
      var newY = (this.y * this.game.playmat.scale.y) + this.game.playmat.y;
      var distance = this.game.math.distance(newX, newY, this.game.input.x, this.game.input.y);
      if(distance > 5) {
        var rotation = this.game.math.angleBetween(newX, newY, this.game.input.x, this.game.input.y);
        this.body.velocity.x = Math.cos(rotation) * 500;
        this.body.velocity.y = Math.sin(rotation) * 500;
      } else {
        this.body.velocity.setTo(0, 0);
      }
    }
  };

  Card.prototype.enableLegacyDragDrop = function() {
    this.input.enableDrag(false, true);
  };

  return Card;
});
