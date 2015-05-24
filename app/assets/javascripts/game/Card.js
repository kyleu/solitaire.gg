define(['utils/Config', 'game/Rank', 'game/Suit', 'game/helpers/CardInput', 'game/helpers/Tweens'], function (cfg, Rank, Suit, CardInput, Tweens) {
  "use strict";

  function Card(game, id, rank, suit, faceUp) {
    this.id = id;

    this.rank = Rank.fromChar(rank);
    this.suit = Suit.fromChar(suit);
    this.faceUp = faceUp;

    this.inputOriginalPosition = null;
    this.animation = null;
    this.tweening = false;

    var initialX = (game.width / 2) / game.playmat.scale.x;
    var initialY = game.height / game.playmat.scale.y;

    this.spriteIndex = (this.suit.index * 13) + (this.rank.value - 2);
    this.cardBackIndex = (4 * 13) + 0;
    if(this.faceUp) {
      Phaser.Sprite.call(this, game, initialX, initialY, 'card', this.spriteIndex);
    } else {
      Phaser.Sprite.call(this, game, initialX, initialY, 'card', this.cardBackIndex);
    }

    this.anchor.setTo(0.5, 0.5);

    this.inputEnabled = true;
    this.dragging = false;
    this.inertiaHistory = [];

    this.game.addCard(this);

    this.events.onInputDown.add(this.onInputDown, this);
    this.events.onInputUp.add(this.onInputUp, this);
  }

  Card.prototype = Object.create(Phaser.Sprite.prototype);
  Card.prototype.constructor = Card;

  Card.prototype.updateSprite = function(faceUp) {
    this.faceUp = faceUp;
    this.spriteIndex = (this.suit.index * 13) + (this.rank.value - 2);
    this.cardBackIndex = (4 * 13) + 0;
    if(this.faceUp) {
      this.loadTexture('card', this.spriteIndex);
    } else {
      this.loadTexture('card', this.cardBackIndex);
    }
  };

  Card.prototype.onInputDown = function(e, p) {
    if(!this.tweening) {
      if(this.pile.canDragFrom(this)) {
        this.pile.startDrag(this, p);
      }
    }
  };

  Card.prototype.startDrag = function(p, dragIndex) {
    CardInput.startDrag(p, dragIndex, this);
  };

  Card.prototype.onInputUp = function(e, p) {
    CardInput.onInputUp(e, p, this);
  };

  Card.prototype.cancelDrag = function() {
    CardInput.cancelDrag(this);
  };

  Card.prototype.update = function() {
    CardInput.update(this);
  };

  Card.prototype.turnFaceUp = function() {
    Tweens.tweenFlip(this, true);
  };

  Card.prototype.turnFaceDown = function() {
    Tweens.tweenFlip(this, false);
  };

  Card.prototype.toString = function() {
    return this.rank.char + this.suit.char + (this.faceUp ? "+" : "-") +": " + this.id.substring(0, 8);
  };

  return Card;
});
