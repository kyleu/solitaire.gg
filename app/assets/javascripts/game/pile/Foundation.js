define(['game/Rank', 'game/pile/Pile', 'game/pile/PileHelpers', 'game/pile/Constraints'], function(Rank, Pile, PileHelpers, Constraints) {
  "use strict";

  var Foundation = function(game, id, cardsShown, direction) {
    Pile.call(this, game, id, "foundation", cardsShown, direction);
  };

  Foundation.prototype = Object.create(Pile.prototype);
  Foundation.prototype.constructor = Foundation;

  Foundation.prototype.canSelectCard = Constraints.returnFalse;
  Foundation.prototype.canSelectPile = Constraints.returnFalse;

  Foundation.prototype.canDragFrom = Constraints.topCardOnly;

  Foundation.prototype.canDragTo = Constraints.klondikeFoundationDragTo;

  Foundation.prototype.startDrag = PileHelpers.dragSlice;
  Foundation.prototype.endDrag = PileHelpers.endDrag;

  return Foundation;
});
