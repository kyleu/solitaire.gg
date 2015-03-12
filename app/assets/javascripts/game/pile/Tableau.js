define(['game/Rank', 'game/pile/Pile', 'game/pile/PileHelpers'], function(Rank, Pile, PileHelpers) {
  "use strict";

  var Tableau = function(game, id, options) {
    Pile.call(this, game, id, "tableau", options);
  };

  Tableau.prototype = Object.create(Pile.prototype);
  Tableau.prototype.constructor = Tableau;

  return Tableau;
});
