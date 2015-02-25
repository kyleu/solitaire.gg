define(function () {
  "use strict";

  function Pile(game, id, behavior) {
    Phaser.Group.call(this, game, game.playmat, id);
    this.id = id;
    this.behavior = behavior;
    this.cards = [];
    this.game.addPile(this);

    this.empty = new Phaser.Sprite(game, 0, 0, 'empty-pile');
    this.empty.inputEnabled = true;
    this.empty.events.onInputUp.add(this.pileSelected, this);
    this.add(this.empty);

    this.intersectWidth = this.empty.width;
    this.intersectHeight = this.empty.height;
  }

  Pile.prototype = Object.create(Phaser.Group.prototype);
  Pile.prototype.constructor = Pile;

  Pile.prototype.addCard = function(card, index) {
    card.x = 0;
    card.pile = this;
    card.pileIndex = this.cards.length;
    this.cards.push(card);

    if(this.behavior == "stock") {
      card.y = this.y;
      card.x = this.x;
    } else if(this.behavior == "waste") {
      card.y = this.y;
      if(this.cards.length === 1) {
        card.x = this.x;
      } else if(this.cards.length === 2) {
        this.cards[0].x = this.x;
        this.cards[1].x = this.x + this.game.cardSet.cardHorizontalOffset;
      } else {
        for(var cardIndex in this.cards) {
          var cardIndexInt = parseInt(cardIndex);
          if(cardIndexInt === this.cards.length - 1) {
            this.cards[cardIndex].x = this.x + (this.game.cardSet.cardHorizontalOffset * 2);
          } else if(cardIndexInt === this.cards.length - 2) {
            this.cards[cardIndex].x = this.x + this.game.cardSet.cardHorizontalOffset;
          } else {
            this.cards[cardIndex].x = this.x;
          }
        }
      }

      var additionalWidth = this.cards.length - 1;
      if(this.cards.length > 3) {
        additionalWidth = 2;
      }
      this.intersectWidth = this.game.cardSet.cardWidth + (additionalWidth * this.game.cardSet.cardHorizontalOffset);
    } else if(this.behavior == "tableau") {
      card.y = this.y + ((this.cards.length - 1) * this.game.cardSet.cardVerticalOffset);
      card.x = this.x;
      this.intersectHeight = this.game.cardSet.cardHeight + (this.cards.length === 0 ? 0 : (this.cards.length - 1) * this.game.cardSet.cardVerticalOffset);
    } else if(this.behavior == "foundation") {
      card.y = this.y;
      card.x = this.x;
    } else {
      throw "Unknown deck behavior [" + this.behavior + "].";
    }

    this.game.playmat.add(card);
  };

  Pile.prototype.removeCard = function(card) {
    var index = this.cards.indexOf(card);
    if(card.pile !== this || index === -1) {
      throw "Provided card is not a part of this pile.";
    }

    card.pile = null;
    card.pileIndex = 0;
    this.cards.splice(index, 1);

    for(var cardIndex in this.cards) {
      this.cards[cardIndex].pileIndex = parseInt(cardIndex);
    }

    if(this.behavior == "waste") {
      if(this.cards.length === 1) {
        this.cards[0].x = this.x;
      } else if(this.cards.length === 2) {
        this.cards[0].x = this.x;
        this.cards[1].x = this.x + this.game.cardSet.cardHorizontalOffset;
      } else {
        for(var ci in this.cards) {
          var cardIndexInt = parseInt(ci);
          if(cardIndexInt === this.cards.length - 1) {
            this.cards[ci].x = this.x + (this.game.cardSet.cardHorizontalOffset * 2);
          } else if(cardIndexInt === this.cards.length - 2) {
            this.cards[ci].x = this.x + this.game.cardSet.cardHorizontalOffset;
          } else {
            this.cards[ci].x = this.x;
          }
        }
      }

      var additionalWidth = this.cards.length - 1;
      if(this.cards.length > 3) {
        additionalWidth = 2;
      }
      this.intersectWidth = this.game.cardSet.cardWidth + (additionalWidth * this.game.cardSet.cardHorizontalOffset);
    } else if(this.behavior == "tableau") {
      this.intersectHeight = this.game.cardSet.cardHeight + (this.cards.length === 0 ? 0 : (this.cards.length - 1) * this.game.cardSet.cardVerticalOffset);
    } else {

    }
  };

  Pile.prototype.startDrag = function(card, p) {
    if(this.behavior == "stock") {
      // No op
    } else {
      this.dragCards = this.cards.slice(card.pileIndex);
      for(var c in this.dragCards) {
        this.dragCards[c].startDrag(p, 0);
      }
    }
  };

  Pile.prototype.endDrag = function() {
    var firstCard = this.dragCards[0];

    var minX = firstCard.x;
    var maxX = firstCard.x + firstCard.width;
    var xPoint = firstCard.x + firstCard.anchorPointX;
    var minY = firstCard.y;
    var maxY = firstCard.y + firstCard.height;
    var yPoint = firstCard.y + firstCard.anchorPointY;

    var dropTarget = null;
    var dropDistance = 65536;

    for(var pileIndex in this.game.piles) {
      var pile = this.game.piles[pileIndex];
      var overlapX = 0;
      if((minX > pile.x && minX < pile.x + pile.intersectWidth) || (maxX > pile.x && maxX < pile.x + pile.intersectWidth)) {
        overlapX = Math.abs(pile.x + (pile.intersectWidth / 2) - xPoint);
      }
      var overlapY = 0;
      if((minY > pile.y && minY < pile.y + pile.intersectHeight) || (maxY > pile.y && maxY < pile.y + pile.intersectHeight)) {
        overlapY = Math.abs(pile.y + (pile.intersectHeight / 2) - yPoint);
      }
      if(overlapX > 0 && overlapY > 0) {
        if(overlapX + overlapY < dropDistance) {
          dropDistance = overlapX + overlapY;
          dropTarget = pile;
        }
      }
    }

    if(dropTarget === null) {
      for(var cancelIndex in this.dragCards) {
        var cancelCard = this.dragCards[cancelIndex];
        cancelCard.dragging = false;
        cancelCard.cancelDrag();
      }
    } else {
      console.log("Moving [" + this.dragCards + "] to [" + dropTarget.id + "].");

      for(var moveIndex in this.dragCards) {
        var moveCard = this.dragCards[moveIndex];
        moveCard.dragging = false;
      }

      var cardIds = [];
      for(var dragCardIndex in this.dragCards) {
        cardIds.push(this.dragCards[dragCardIndex].id);
      }

      this.game.ws.send("MoveCards", { cards: cardIds, src: this.id, tgt: dropTarget.id });
    }
    this.dragCards = [];
  };

  Pile.prototype.cardSelected = function(c) {
    this.game.cardSelected(c);
  };

  Pile.prototype.pileSelected = function(pile, p) {
    this.game.pileSelected(this, p);
  };

  return Pile;
});
