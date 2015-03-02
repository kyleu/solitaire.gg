define(['game/pile/Pile', 'game/pile/PileHelpers'], function(Pile, PileHelpers) {
  "use strict";

  var Stock = function(game, id) {
    Pile.call(this, game, id, "stock");
  };

  Stock.prototype = Object.create(Pile.prototype);
  Stock.prototype.constructor = Stock;

  Stock.prototype.canSelect = PileHelpers.topCardOnly;

  Stock.prototype.canDragFrom = PileHelpers.returnFalse;

  Stock.prototype.canDragTo = PileHelpers.returnFalse;

  Stock.prototype.cardAdded = PileHelpers.samePosition;
  Stock.prototype.cardRemoved = PileHelpers.noOp;

  Stock.prototype.startDrag = PileHelpers.noOp;
  Stock.prototype.endDrag = PileHelpers.noOp;

  return Stock;
});
