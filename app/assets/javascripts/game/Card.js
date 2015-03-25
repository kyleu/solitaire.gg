define(["game/Rank", "game/Suit"], function (Rank, Suit) {
  "use strict";

  function canSelectCard(card) {
    var valid = false;
    for(var moveIndex in card.game.possibleMoves) {
      var move = card.game.possibleMoves[moveIndex];
      if(move.moveType === "select-card" && move.sourcePile === card.pile.id) {
        if(move.cards.length === 1 && move.cards[0] === card.id) {
          valid = true;
        }
      }
    }
    return valid;
  }

  function Card(game, id, rank, suit, faceUp) {
    this.id = id;

    this.rank = Rank.fromChar(rank);
    this.suit = Suit.fromChar(suit);
    this.faceUp = faceUp;

    this.inputOriginalPosition = null;
    this.animation = null;
    this.tweening = false;

    this.spriteIndex = (this.suit.index * 13) + (this.rank.value - 2);
    if(this.faceUp) {
      Phaser.Sprite.call(this, game, 0, 0, 'card', this.spriteIndex);
    } else {
      Phaser.Sprite.call(this, game, 0, 0, 'card-back', 0);
    }

    this.inputEnabled = true;
    this.dragging = false;

    this.game.addCard(this);

    this.events.onInputDown.add(this.onInputDown, this);
    this.events.onInputUp.add(this.onInputUp, this);
  }

  Card.prototype = Object.create(Phaser.Sprite.prototype);
  Card.prototype.constructor = Card;

  Card.prototype.updateSprite = function(rank, suit, faceUp) {
    this.rank = Rank.fromChar(rank);
    this.suit = Suit.fromChar(suit);
    this.faceUp = faceUp;
    this.spriteIndex = (this.suit.index * 13) + (this.rank.value - 2);
    if(this.faceUp) {
      this.frame = this.spriteIndex;
      this.key = 'card';
    } else {
      this.frame = 0;
      this.key = 'card-back';
    }
    this.updateCache();
  };

  Card.prototype.onInputDown = function(e, p) {
    if(!this.tweening) {
      if(this.pile.canDragFrom(this)) {
        this.pile.startDrag(this, p);
      }
    }
  };

  Card.prototype.startDrag = function(p) {
    this.dragging = true;
    this.inputOriginalPosition = this.position.clone();
    this.anchorPointX = ((p.positionDown.x - this.game.playmat.x) / this.game.playmat.scale.x) - this.x;
    this.anchorPointY = ((p.positionDown.y - this.game.playmat.y) / this.game.playmat.scale.y) - this.y;
    this.bringToTop();
  };

  Card.prototype.onInputUp = function(e, p) {
    if(this.dragging) {
      this.pile.endDrag();
    } else {
      var deltaX = Math.abs(p.positionDown.x - p.positionUp.x);
      var deltaY = Math.abs(p.positionDown.y - p.positionUp.y);
      if(deltaX < 5 && deltaY < 5) {
        if(canSelectCard(this)) {
          this.pile.cardSelected(this);
        }
      }
    }
  };

  Card.prototype.cancelDrag = function() {
    if(this.inputOriginalPosition !== null) {
      var xDelta = Math.abs(this.x - this.inputOriginalPosition.x);
      var yDelta = Math.abs(this.y - this.inputOriginalPosition.y);
      if(xDelta > 5 || yDelta > 5) {
        // Dragged
        this.game.add.tween(this).to({x: this.inputOriginalPosition.x, y: this.inputOriginalPosition.y}, 500, Phaser.Easing.Quadratic.InOut, true);
      } else {
        if(canSelectCard(this)) {
          this.pile.cardSelected(this);
        }
      }
      this.inputOriginalPosition = null;
    } else {
      if(canSelectCard(this)) {
        this.pile.cardSelected(this);
      }
    }
  };

  Card.prototype.turnFaceUp = function() {

    this.faceUp = true;

    this.loadTexture('card', this.spriteIndex);
  };

  Card.prototype.turnFaceDown = function() {
    this.faceUp = false;
    this.loadTexture('card-back', 0);
  };

  Card.prototype.toString = function() {
    return this.rank.char + this.suit.char + (this.faceUp ? "+" : "-") +": " + this.id.substring(0, 8);
  };

  Card.prototype.update = function() {
    if(this.animation === null) {
      if(this.dragging) {
        this.x = ((this.game.input.x - this.game.playmat.x) / this.game.playmat.scale.x) - (this.anchorPointX);
        this.y = ((this.game.input.y - this.game.playmat.y) / this.game.playmat.scale.y) - (this.anchorPointY);
      }
    } else if(this.animation.id === "mouse") {
      var tgtX = this.game.input.x - ((this.width / 2) * this.game.playmat.scale.x);
      var tgtY = this.game.input.y - ((this.height / 2) * this.game.playmat.scale.y);
      var distance = this.game.math.distance(this.world.x, this.world.y, tgtX, tgtY);
      if(distance > 20) {
        var rotation = this.game.math.angleBetween(this.world.x, this.world.y, tgtX, tgtY);
        this.x += Math.cos(rotation) * 10;
        this.y += Math.sin(rotation) * 10;
      }
    }
  };

  return Card;
});
