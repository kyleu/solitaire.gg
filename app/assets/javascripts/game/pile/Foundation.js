define(['game/Rank', 'game/pile/Pile', 'game/pile/PileHelpers'], function(Rank, Pile, PileHelpers) {
  "use strict";

  var Foundation = function(game, id, options) {
    Pile.call(this, game, id, "foundation", options);
  };

  Foundation.prototype = Object.create(Pile.prototype);
  Foundation.prototype.constructor = Foundation;

  return Foundation;
});
