define(['game/Rank', 'game/pile/Pile', 'game/pile/PileHelpers'], function(Rank, Pile, PileHelpers) {
  "use strict";

  var Foundation = function(game, id, cardsShown, direction) {
    Pile.call(this, game, id, "foundation", cardsShown, direction);
  };

  Foundation.prototype = Object.create(Pile.prototype);
  Foundation.prototype.constructor = Foundation;

  Foundation.prototype.canSelectCard = PileHelpers.returnFalse;
  Foundation.prototype.canSelectPile = PileHelpers.returnFalse;

  Foundation.prototype.canDragFrom = PileHelpers.topCardOnly;

  Foundation.prototype.canDragTo = function(pile) {
    if(pile.dragCards.length == 1) {
      if(this.cards.length === 0) {
        return pile.dragCards[0].rank === Rank.ace;
      } else {
        var topCard = this.cards[this.cards.length - 1];
        var dragCard = pile.dragCards[0];
        return topCard.suit === dragCard.suit && ((topCard.rank === Rank.ace && dragCard.rank === Rank.two) || topCard.rank.value + 1 == dragCard.rank.value);
      }
    } else {
      return false;
    }
  };

  Foundation.prototype.startDrag = PileHelpers.dragSlice;

  Foundation.prototype.endDrag = PileHelpers.endDrag;

  Foundation.prototype.cardAdded = PileHelpers.samePosition;

  Foundation.prototype.cardRemoved = PileHelpers.noOp;

  return Foundation;
});
