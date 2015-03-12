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

  return Waste;
});
