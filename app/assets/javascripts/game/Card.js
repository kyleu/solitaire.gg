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
    this.inputDown = false;

    this.game.addCard(this);
    this.game.physics.arcade.enable(this);

    this.events.onInputDown.add(this.onInputDown, this);
    this.events.onInputUp.add(this.onInputUp, this);
  }

  Card.prototype = Object.create(Phaser.Sprite.prototype);
  Card.prototype.constructor = Card;

  Card.prototype.onInputDown = function(e, p) {
    this.inputDown = true;
    this.inputOriginalPosition = this.position.clone();
  };

  Card.prototype.onInputUp = function(e, p) {
    this.inputDown = false;
    var xDelta = Math.abs(p.positionDown.x - p.positionUp.x);
    var yDelta = Math.abs(p.positionDown.y - p.positionUp.y);
    if(xDelta > 5 || yDelta > 5) {
      // Dragged
      this.x = this.inputOriginalPosition.x;
      this.y = this.inputOriginalPosition.y;
    } else {
      this.pile.cardSelected(e, p);
    }
  };

  Card.prototype.toString = function() {
    return this.rank.char + this.suit.char + ": " + this.id.substring(0, 8);
  };

  Card.prototype.update = function() {
    if(this.animation === null) {
      if(this.inputDown) {
        this.x = ((this.game.input.x - this.game.playmat.x) / this.game.playmat.scale.x) - ((this.width / 2));
        this.y = ((this.game.input.y - this.game.playmat.y) / this.game.playmat.scale.y) - ((this.height / 2));
      }
    } else if(this.animation.id === "mouse") {
      var tgtX = this.game.input.x - ((this.width / 2) * this.game.playmat.scale.x);
      var tgtY = this.game.input.y - ((this.height / 2) * this.game.playmat.scale.y);
      var distance = this.game.math.distance(this.world.x, this.world.y, tgtX, tgtY);
      if(distance > 5) {
        var rotation = this.game.math.angleBetween(this.world.x, this.world.y, tgtX, tgtY);
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
