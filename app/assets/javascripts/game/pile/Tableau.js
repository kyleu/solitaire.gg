define(['game/Rank', 'game/pile/Pile', 'game/pile/PileHelpers', 'game/pile/Constraints'], function(Rank, Pile, PileHelpers, Constraints) {
  "use strict";

  var Tableau = function(game, id, cardsShown, direction, options) {
    Pile.call(this, game, id, "tableau", cardsShown, direction, options);
  };

  Tableau.prototype = Object.create(Pile.prototype);
  Tableau.prototype.constructor = Tableau;

  Tableau.prototype.canSelectCard = Constraints.topCardOnly;
  Tableau.prototype.canSelectPile = Constraints.returnFalse;

  Tableau.prototype.canDragFrom = Constraints.klondikeTableauDragFrom;

  Tableau.prototype.canDragTo = Constraints.klondikeTableauDragTo;

  Tableau.prototype.startDrag = PileHelpers.dragSlice;

  Tableau.prototype.endDrag = PileHelpers.endDrag;

  return Tableau;
});
