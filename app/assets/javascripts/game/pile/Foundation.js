define(['game/pile/Pile', 'game/pile/PileHelpers'], function(Pile, PileHelpers) {
  "use strict";

  var Foundation = function(game, id) {
    Pile.call(this, game, id, "foundation");
  };

  Foundation.prototype = Object.create(Pile.prototype);
  Foundation.prototype.constructor = Foundation;

  Foundation.prototype.canSelectCard = PileHelpers.returnFalse;
  Foundation.prototype.canSelectPile = PileHelpers.returnFalse;

  Foundation.prototype.canDragFrom = PileHelpers.topCardOnly;

  Foundation.prototype.canDragTo = function() {
    return true;
  };

  Foundation.prototype.startDrag = PileHelpers.dragSlice;

  Foundation.prototype.endDrag = PileHelpers.endDrag;

  Foundation.prototype.cardAdded = PileHelpers.samePosition;

  Foundation.prototype.cardRemoved = PileHelpers.noOp;

  return Foundation;
});
