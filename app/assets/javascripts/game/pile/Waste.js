define(['game/pile/Pile', 'game/pile/PileHelpers', 'game/pile/Constraints'], function(Pile, PileHelpers, Constraints) {
  "use strict";

  var Waste = function(game, id, options) {
    Pile.call(this, game, id, "waste", options);
  };

  Waste.prototype = Object.create(Pile.prototype);
  Waste.prototype.constructor = Waste;

  Waste.prototype.canSelectCard = Constraints.topCardOnly;
  Waste.prototype.canSelectPile = Constraints.returnFalse;

  Waste.prototype.canDragFrom = Constraints.topCardOnly;
  Waste.prototype.canDragTo = Constraints.returnFalse;

  Waste.prototype.startDrag = PileHelpers.dragSlice;
  Waste.prototype.endDrag = PileHelpers.endDrag;

  return Waste;
});
