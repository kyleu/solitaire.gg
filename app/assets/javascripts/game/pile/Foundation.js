define(['game/pile/Pile', 'game/pile/PileHelpers'], function(Pile, PileHelpers) {
  "use strict";

  var Foundation = function(game, id) {
    Pile.call(this, game, id, "foundation");
  };

  Foundation.prototype = Object.create(Pile.prototype);
  Foundation.prototype.constructor = Foundation;

  Foundation.prototype.canSelect = function() {
    return false;
  };

  Foundation.prototype.canDragFrom = function() {
    return false;
  };

  Foundation.prototype.canDragTo = function() {
    return false;
  };

  Foundation.prototype.startDrag = PileHelpers.dragSlice;

  Foundation.prototype.endDrag = PileHelpers.endDrag;

  Foundation.prototype.cardAdded = PileHelpers.samePosition;

  Foundation.prototype.cardRemoved = PileHelpers.noOp;

  return Foundation;
});
