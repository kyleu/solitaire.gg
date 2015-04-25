define(function () {
  "use strict";

  var margin = 0.6;
  var padding = 0.1;

  function getDimensions(pileSet) {
    var ret = [pileSet.piles.length * (1 + padding), 1 + padding];
    switch(pileSet.behavior) {
      case "tableau":
        ret = [ret[0], 2 * (1 + padding)];
        break;
      case "pryamid":
        ret = [0, 0];
        break;
      default:
    }
    return ret;
  }

  function calculateLayout(pileSets, aspectRatio) {
    console.log("Creating layout for [" + pileSets.length + "] pile sets with aspect ratio of [" + aspectRatio + "].");
    var locations = {};

    var xOffset = margin;
    var yOffset = margin;

    var dimensions = [];

    for(var pileSetIndex in pileSets) {
      var pileSet = pileSets[pileSetIndex];
      var pileSetDimensions = getDimensions(pileSet);
      for(var pileIndex in pileSet.piles) {
        var pile = pileSet.piles[pileIndex];
        locations[pile.id] = { x: xOffset, y: yOffset };
        if(xOffset > 7) {
          xOffset = margin;
          yOffset = yOffset + 1 + padding;
        } else {
          if(pileSet.behavior == "waste") {
            xOffset = xOffset + 2 + (padding * 2);
          } else {
            xOffset = xOffset + 1 + padding;
          }
        }
      }
    }

    var width = 7.9;
    var height = 6.0;

    return { "width": width, "height": height, "locations": locations };
  }

  return calculateLayout;
});
