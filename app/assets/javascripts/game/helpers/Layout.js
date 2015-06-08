define(['game/helpers/Dimensions'], function (getDimensions) {
  "use strict";

  var margin = 0.7;
  var padding = 0.2;

  function calculateLayout(pileSets, layout /* , aspectRatio */) {
    //console.log("Creating layout for [" + pileSets.length + "] pile sets using layout [" + layout + "] with aspect ratio of [" + aspectRatio + "].");
    var locations = {};
    var xOffset = margin;
    var yOffset = 0.6;
    var remainingPileSets = pileSets;
    var currentRowMaxHeight = 1.0;
    var maxWidth = 0;
    var lastChar = null;
    var currentDivisor = 0;

    function newRow() {
      if(xOffset > maxWidth) {
        maxWidth = xOffset;
      }
      xOffset = margin;
      yOffset += currentRowMaxHeight;
      currentRowMaxHeight = 1;
    }

    function processCharacter(c) {
      var pileSet;
      switch(c) {
        case 's': pileSet = _.find(remainingPileSets, function(ps) { return ps.behavior === "stock"; }); break;
        case 'w': pileSet = _.find(remainingPileSets, function(ps) { return ps.behavior === "waste"; }); break;
        case 'f': pileSet = _.find(remainingPileSets, function(ps) { return ps.behavior === "foundation"; }); break;
        case 't': pileSet = _.find(remainingPileSets, function(ps) { return ps.behavior === "tableau"; }); break;
        case 'c': pileSet = _.find(remainingPileSets, function(ps) { return ps.behavior === "cell"; }); break;
        case 'r': pileSet = _.find(remainingPileSets, function(ps) { return ps.behavior === "reserve"; }); break;
        case 'p': pileSet = _.find(remainingPileSets, function(ps) { return ps.behavior === "pyramid"; }); break;
        default:
      }
      switch(c) {
        case ':': xOffset += 1 + padding; break;
        case '.': xOffset += (1 + padding) / 2; break;
        case '|': newRow(); break;
        case '2': currentDivisor = 2; break;
        case '3': currentDivisor = 3; break;
        case '4': currentDivisor = 4; break;
        case '5': currentDivisor = 5; break;
        default:
          if(pileSet === undefined) {
            throw "Unable to find set matching [" + c + "]";
          }
          remainingPileSets = _.without(remainingPileSets, pileSet);
          pileSet.position = [xOffset - 0.5, yOffset - 0.5];
          var pileSetDimensions = getDimensions(pileSet, currentDivisor);
          //console.log(pileSet);
          if(pileSet.visible !== undefined && !pileSet.visible) { // Hide this pile
            _.each(pileSet.piles, function(pile, pileIndex) {
              //console.log("Adding [" + pile.id + "]!");
              locations[pile.id] = {x: (pileIndex * (1 + padding)) + 0.5, y: -10};
            });
          } else {
            if(pileSetDimensions[1] > currentRowMaxHeight) {
              currentRowMaxHeight = pileSetDimensions[1];
            }
            if(pileSet.behavior === "pyramid") {
              var currentRow = 1;
              var rowCounter = 0;
              xOffset = margin + ((pileSet.rows - currentRow) / 2) * (1 + padding);
              _.each(pileSet.piles, function(pile) {
                if(rowCounter === currentRow) {
                  currentRow += 1;
                  rowCounter -= rowCounter;
                  xOffset = margin + ((pileSet.rows - currentRow) / 2) * (1 + padding);
                }

                locations[pile.id] = {x: xOffset, y: yOffset + ((currentRow - 1) * 0.5)};
                xOffset = xOffset + 1 + padding;
                rowCounter += 1;
              });
            } else {
              var originalXOffset = xOffset;
              var groupSize = pileSet.piles.length;
              if(currentDivisor > 0) {
                groupSize = pileSet.piles.length / currentDivisor;
              }
              _.each(pileSet.piles, function(pile, pileIndex) {
                if(pileIndex > 0 && pileIndex % groupSize === 0) {
                  newRow();
                  currentRowMaxHeight = pileSetDimensions[1];
                }
                locations[pile.id] = {x: xOffset, y: yOffset};
                xOffset = xOffset + 1 + padding;
              });
              xOffset = originalXOffset + pileSetDimensions[0];
            }
          }
          currentDivisor = 0;
          break;
      }
      lastChar = c;
    }
    _.each(layout.split(''), function(char) {
      processCharacter(char);
    });
    newRow();
    return { "width": maxWidth - margin + padding, "height": yOffset - 0.5, "locations": locations };
  }
  return calculateLayout;
});
