define(function () {
  "use strict";

  var noOp = function() {};

  var returnFalse = function() { return false; };

  var topCardOnly = function(pile, card) {
    return card.pileIndex == pile.cards.length - 1;
  };

  var tableauCanDrag = function(pile, card) {
    return true;
  };

  var dragSlice = function(pile, card, p) {
    pile.dragCards = pile.cards.slice(card.pileIndex);
    for(var c in pile.dragCards) {
      pile.dragCards[c].startDrag(p, 0);
    }
  };

  var getDropTarget = function(pile) {
    var firstCard = pile.dragCards[0];

    var minX = firstCard.x;
    var maxX = firstCard.x + firstCard.width;
    var xPoint = firstCard.x + firstCard.anchorPointX;
    var minY = firstCard.y;
    var maxY = firstCard.y + firstCard.height;
    var yPoint = firstCard.y + firstCard.anchorPointY;

    var dropTarget = null;
    var dropDistance = 65536;

    for(var pileIndex in pile.game.piles) {
      var p = pile.game.piles[pileIndex];
      if(p.id !== pile.id) {
        var overlapX = 0;
        if((minX > p.x && minX < p.x + p.intersectWidth) || (maxX > p.x && maxX < p.x + p.intersectWidth)) {
          overlapX = Math.abs(p.x + (p.intersectWidth / 2) - xPoint);
        }
        var overlapY = 0;
        if((minY > p.y && minY < p.y + p.intersectHeight) || (maxY > p.y && maxY < p.y + p.intersectHeight)) {
          overlapY = Math.abs(p.y + (p.intersectHeight / 2) - yPoint);
        }
        if(overlapX > 0 && overlapY > 0) {
          if(overlapX + overlapY < dropDistance) {
            dropDistance = overlapX + overlapY;
            dropTarget = p;
          }
        }
      }
    }
    return dropTarget;
  };

  var endDrag = function(pile) {
    var dropTarget = getDropTarget(pile);

    if(dropTarget === null) {
      for(var cancelIndex in pile.dragCards) {
        var cancelCard = pile.dragCards[cancelIndex];
        cancelCard.dragging = false;
        cancelCard.cancelDrag();
      }
    } else {
      console.log("Moving [" + pile.dragCards + "] to [" + dropTarget.id + "].");

      for(var moveIndex in pile.dragCards) {
        var moveCard = pile.dragCards[moveIndex];
        moveCard.dragging = false;
      }

      var cardIds = [];
      for(var dragCardIndex in pile.dragCards) {
        cardIds.push(pile.dragCards[dragCardIndex].id);
      }

      pile.game.ws.send("MoveCards", { cards: cardIds, src: pile.id, tgt: dropTarget.id });
    }
    pile.dragCards = [];
  };

  return {
    stock: {
      canDrag: returnFalse,
      startDrag: noOp,
      endDrag: endDrag
    },
    waste: {
      canDrag: topCardOnly,
      startDrag: dragSlice,
      endDrag: endDrag
    },
    tableau: {
      canDrag: tableauCanDrag,
      startDrag: dragSlice,
      endDrag: endDrag
    },
    foundation: {
      canDrag: topCardOnly,
      startDrag: dragSlice,
      endDrag: endDrag
    }
  };
});
