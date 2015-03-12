define(['game/pile/Pile'], function(Pile) {
  "use strict";

  var Stock = function(game, id, options) {
    Pile.call(this, game, id, "stock", options);
  };

  Stock.prototype = Object.create(Pile.prototype);
  Stock.prototype.constructor = Stock;

  return Stock;
});
