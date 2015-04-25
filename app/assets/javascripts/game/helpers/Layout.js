define(function () {
  "use strict";

  var margin = 0.6;
  var padding = 0.1;

  function getDimensions(pileSet) {
    var ret = [pileSet.piles.length * (1 + padding), 1 + padding];
    switch(pileSet.behavior) {
      case "tableau":
        var maxCards = _.max(pileSet.piles, function(pile) { return pile.cards.length; }).cards.length;
        ret = [ret[0], 1 + padding + (maxCards * padding)];
        break;
      case "pryamid":
        ret = [0, 0];
        break;
      default:
    }
    return ret;
  }

  function calculateLayout(pileSets, layout, aspectRatio) {
    console.log("Creating layout for [" + pileSets.length + "] pile sets using layout [" + layout + "] with aspect ratio of [" + aspectRatio + "].");
    var locations = {};

    var xOffset = margin;
    var yOffset = margin;

    var remainingPileSets = pileSets;

    var currentRowMaxHeight = 1.0;

    var maxWidth = 0;

    function processCharacter(c) {
      var pileSet;
      switch(c) {
        case 's': pileSet = _.find(remainingPileSets, function(ps) { return ps.behavior === "stock"; }); break;
        case 'w': pileSet = _.find(remainingPileSets, function(ps) { return ps.behavior === "waste"; }); break;
        case 'f': pileSet = _.find(remainingPileSets, function(ps) { return ps.behavior === "foundation"; }); break;
        case 't': pileSet = _.find(remainingPileSets, function(ps) { return ps.behavior === "tableau"; }); break;
        case 'c': pileSet = _.find(remainingPileSets, function(ps) { return ps.behavior === "cell"; }); break;
        default:
      }
      switch(c) {
        case ':':
          xOffset += 1 + padding;
          break;
        case '.':
          xOffset += (1 + padding) / 2;
          break;
        default:
          if(pileSet === undefined) {
            throw "Unable to find set matching [" + c + "]";
          }
          remainingPileSets = _.without(remainingPileSets, pileSet);
          var pileSetDimensions = getDimensions(pileSet);
          _.each(pileSet.piles, function(pile) {
            locations[pile.id] = { x: xOffset, y: yOffset };
            xOffset = xOffset + 1 + padding;
          });
          if(pileSetDimensions[1] > currentRowMaxHeight) {
            currentRowMaxHeight = pileSetDimensions[1];
          }
      }
    }

    function newRow() {
      if(xOffset > maxWidth) {
        maxWidth = xOffset;
      }
      xOffset = margin;
      yOffset += currentRowMaxHeight;
      currentRowMaxHeight = 1;
    }

    _.each(layout, function(line) {
      _.each(line.split(''), function(char) {
        processCharacter(char);
      });
      newRow();
    });

    return { "width": maxWidth + padding, "height": yOffset, "locations": locations };
  }

  return calculateLayout;
});
