define(['game/pile/Pile', 'game/pile/PileHelpers'], function(Pile, PileHelpers) {
  "use strict";

  var Stock = function(game, id, cardsShown, direction) {
    Pile.call(this, game, id, "stock", cardsShown, direction);
  };

  Stock.prototype = Object.create(Pile.prototype);
  Stock.prototype.constructor = Stock;

  Stock.prototype.canSelectCard = PileHelpers.topCardOnly;
  Stock.prototype.canSelectPile = PileHelpers.isEmpty;

  Stock.prototype.canDragFrom = PileHelpers.returnFalse;

  Stock.prototype.canDragTo = PileHelpers.returnFalse;

  Stock.prototype.startDrag = PileHelpers.noOp;
  Stock.prototype.endDrag = PileHelpers.noOp;

  return Stock;
});
