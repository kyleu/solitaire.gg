define(function () {
  "use strict";

  var padding = 0.2;

  function getDimensions(pileSet, divisor, game) {
    //if(pileSet.dimensions !== undefined) {
    //  return pileSet.dimensions;
    //}
    //console.log("Calculating dimensions for: " + pileSet.behavior);
    var ret = [pileSet.piles.length * (1 + padding), 1 + padding];
    switch(pileSet.behavior) {
      case "waste":
        var wasteCardsShown = pileSet.piles[0].options.cardsShown;
        if(wasteCardsShown === 3) {
          ret = [2 * (1 + padding), ret[1]];
        } else {
          ret = [1 + (wasteCardsShown * padding * 1.5), ret[1]];
        }
        break;
      case "reserve":
      case "tableau":
        var overlappedCards = Math.max(_.max(pileSet.piles, function(pile) { return pile.cards.length; }).cards.length, 1);
        if(divisor > 1) {
          ret = [ret[0] / divisor, 1 + ((overlappedCards) * padding)];
        } else {
          ret = [ret[0], 1 + ((overlappedCards) * padding)];
        }
        break;
      case "pyramid":
        var rows = 1;
        var rowCounter = 0;
        _.each(pileSet.piles, function() {
          if(rowCounter === rows) {
            rows += 1;
            rowCounter -= rowCounter;
          }

          rowCounter += 1;
        });
        pileSet.rows = rows;
        ret = [rows * (1 + padding), (rows * 0.5) + 0.5 + padding];
        break;
      default:
    }
    pileSet.dimensions = ret;
    pileSet.divisor = divisor;
    return ret;
  }

  return getDimensions;
});
