define(['game/pile/Pile', 'game/pile/PileHelpers'], function(Pile, PileHelpers) {
  "use strict";

  var Tableau = function(game, id) {
    Pile.call(this, game, id, "tableau");
  };

  Tableau.prototype = Object.create(Pile.prototype);
  Tableau.prototype.constructor = Tableau;

  Tableau.prototype.canSelect = PileHelpers.returnFalse;

  Tableau.prototype.canDragFrom = function() {
    return true;
  };

  Tableau.prototype.canDragTo = function() {
    return true;
  };

  Tableau.prototype.startDrag = PileHelpers.dragSlice;

  Tableau.prototype.endDrag = PileHelpers.endDrag;

  Tableau.prototype.cardAdded = function(pile, card) {
    var tween = card.game.add.tween(card);
    tween.to({
      x: pile.x,
      y: pile.y + ((pile.cards.length - 1) * pile.game.cardSet.cardVerticalOffset)
    }, 200);
    tween.start();
    pile.intersectHeight = pile.game.cardSet.cardHeight + (pile.cards.length === 0 ? 0 : (pile.cards.length - 1) * pile.game.cardSet.cardVerticalOffset);
  };

  Tableau.prototype.cardRemoved = function(pile) {
    pile.intersectHeight = pile.game.cardSet.cardHeight + (pile.cards.length === 0 ? 0 : (pile.cards.length - 1) * pile.game.cardSet.cardVerticalOffset);
  };

  return Tableau;
});
