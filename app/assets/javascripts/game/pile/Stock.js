define(['game/pile/Pile', 'game/pile/Constraints'], function(Pile, Constraints) {
  "use strict";

  var Stock = function(game, id, cardsShown, direction) {
    Pile.call(this, game, id, "stock", cardsShown, direction);
  };

  Stock.prototype = Object.create(Pile.prototype);
  Stock.prototype.constructor = Stock;

  Stock.prototype.canSelectCard = Constraints.topCardOnly;
  Stock.prototype.canSelectPile = Constraints.isEmpty;

  Stock.prototype.canDragFrom = Constraints.returnFalse;

  Stock.prototype.canDragTo = Constraints.returnFalse;

  Stock.prototype.startDrag = Constraints.noOp;
  Stock.prototype.endDrag = Constraints.noOp;

  return Stock;
});
