define(['game/pile/Pile', 'game/pile/PileHelpers'], function(Pile, PileHelpers) {
  "use strict";

  var Waste = function(game, id, cardsShown, direction) {
    Pile.call(this, game, id, "waste", cardsShown, direction);
  };

  Waste.prototype = Object.create(Pile.prototype);
  Waste.prototype.constructor = Waste;

  Waste.prototype.canSelectCard = PileHelpers.topCardOnly;
  Waste.prototype.canSelectPile = PileHelpers.returnFalse;

  Waste.prototype.canDragFrom = PileHelpers.topCardOnly;

  Waste.prototype.canDragTo = PileHelpers.returnFalse;

  Waste.prototype.startDrag = PileHelpers.dragSlice;

  Waste.prototype.endDrag = PileHelpers.endDrag;

  Waste.prototype.cardAdded = function(card) {
    var y = this.y;
    if(this.cards.length === 1) {
      PileHelpers.tweenCard(this.cards[0], this.x, y);
    } else if(this.cards.length === 2) {
      PileHelpers.tweenCard(this.cards[0], this.x, y);
      PileHelpers.tweenCard(this.cards[1], this.x + this.game.cardSet.cardHorizontalOffset, y);
    } else {
      for(var cardIndex in this.cards) {
        var cardIndexInt = parseInt(cardIndex);
        if(cardIndexInt === this.cards.length - 1) {
          PileHelpers.tweenCard(this.cards[cardIndex], this.x + (this.game.cardSet.cardHorizontalOffset * 2), y);
        } else if(cardIndexInt === this.cards.length - 2) {
          PileHelpers.tweenCard(this.cards[cardIndex], this.x + this.game.cardSet.cardHorizontalOffset, y);
        } else {
          PileHelpers.tweenCard(this.cards[cardIndex], this.x);
        }
      }
    }

    var additionalWidth = this.cards.length - 1;
    if(this.cards.length > 3) {
      additionalWidth = 2;
    }
    this.intersectWidth = this.game.cardSet.cardWidth + (additionalWidth * this.game.cardSet.cardHorizontalOffset);
  };

  Waste.prototype.cardRemoved = function(card) {
    if(this.cards.length === 1) {
      PileHelpers.tweenCard(this.cards[0], this.x, this.y);
    } else if(this.cards.length === 2) {
      PileHelpers.tweenCard(this.cards[0], this.x, this.y);
      PileHelpers.tweenCard(this.cards[1], this.x + this.game.cardSet.cardHorizontalOffset, this.y);
    } else {
      for(var ci in this.cards) {
        var cardIndexInt = parseInt(ci);
        if(cardIndexInt === this.cards.length - 1) {
          PileHelpers.tweenCard(this.cards[ci], this.x + (this.game.cardSet.cardHorizontalOffset * 2), this.y);
        } else if(cardIndexInt === this.cards.length - 2) {
          PileHelpers.tweenCard(this.cards[ci], this.x + this.game.cardSet.cardHorizontalOffset, this.y);
        } else {
          PileHelpers.tweenCard(this.cards[ci], this.x, this.y);
        }
      }
    }

    var additionalWidth = this.cards.length - 1;
    if(this.cards.length > 3) {
      additionalWidth = 2;
    }
    this.intersectWidth = this.game.cardSet.cardWidth + (additionalWidth * this.game.cardSet.cardHorizontalOffset);
  };

  return Waste;
});
