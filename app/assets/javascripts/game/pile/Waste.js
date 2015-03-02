define(['game/pile/Pile', 'game/pile/PileHelpers'], function(Pile, PileHelpers) {
  "use strict";

  var Waste = function(game, id) {
    Pile.call(this, game, id, "waste");
  };

  Waste.prototype = Object.create(Pile.prototype);
  Waste.prototype.constructor = Waste;

  Waste.prototype.canSelect = PileHelpers.returnFalse;

  Waste.prototype.canDragFrom = PileHelpers.topCardOnly;

  Waste.prototype.canDragTo = PileHelpers.returnFalse;

  Waste.prototype.startDrag = PileHelpers.dragSlice;

  Waste.prototype.endDrag = PileHelpers.endDrag;

  Waste.prototype.cardAdded = function(pile) {
    var y = pile.y;
    if(pile.cards.length === 1) {
      PileHelpers.tweenCard(pile.cards[0], pile.x, y);
    } else if(pile.cards.length === 2) {
      PileHelpers.tweenCard(pile.cards[0], pile.x, y);
      PileHelpers.tweenCard(pile.cards[1], pile.x + pile.game.cardSet.cardHorizontalOffset, y);
    } else {
      for(var cardIndex in pile.cards) {
        var cardIndexInt = parseInt(cardIndex);
        if(cardIndexInt === pile.cards.length - 1) {
          PileHelpers.tweenCard(pile.cards[cardIndex], pile.x + (pile.game.cardSet.cardHorizontalOffset * 2), y);
        } else if(cardIndexInt === pile.cards.length - 2) {
          PileHelpers.tweenCard(pile.cards[cardIndex], pile.x + pile.game.cardSet.cardHorizontalOffset, y);
        } else {
          PileHelpers.tweenCard(pile.cards[cardIndex], pile.x);
        }
      }
    }

    var additionalWidth = pile.cards.length - 1;
    if(pile.cards.length > 3) {
      additionalWidth = 2;
    }
    pile.intersectWidth = pile.game.cardSet.cardWidth + (additionalWidth * pile.game.cardSet.cardHorizontalOffset);
  };

  Waste.prototype.cardRemoved = function(pile) {
    if(pile.cards.length === 1) {
      PileHelpers.tweenCard(pile.cards[0], pile.x, pile.y);
    } else if(pile.cards.length === 2) {
      PileHelpers.tweenCard(pile.cards[0], pile.x, pile.y);
      PileHelpers.tweenCard(pile.cards[1], pile.x + pile.game.cardSet.cardHorizontalOffset, pile.y);
    } else {
      for(var ci in pile.cards) {
        var cardIndexInt = parseInt(ci);
        if(cardIndexInt === pile.cards.length - 1) {
          PileHelpers.tweenCard(pile.cards[ci], pile.x + (pile.game.cardSet.cardHorizontalOffset * 2), pile.y);
        } else if(cardIndexInt === pile.cards.length - 2) {
          PileHelpers.tweenCard(pile.cards[ci], pile.x + pile.game.cardSet.cardHorizontalOffset, pile.y);
        } else {
          PileHelpers.tweenCard(pile.cards[ci], pile.x, pile.y);
        }
      }
    }

    var additionalWidth = pile.cards.length - 1;
    if(pile.cards.length > 3) {
      additionalWidth = 2;
    }
    pile.intersectWidth = pile.game.cardSet.cardWidth + (additionalWidth * pile.game.cardSet.cardHorizontalOffset);
  };

  return Waste;
});
