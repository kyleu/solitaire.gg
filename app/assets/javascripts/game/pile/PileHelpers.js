define(function() {
  "use strict";

  var isValidMove = function(src, tgt) {
    var valid = false;
    for(var moveIndex in src.game.possibleMoves) {
      var move = src.game.possibleMoves[moveIndex];
      if(move.moveType === "move-cards" && move.sourcePile === src.id && move.targetPile === tgt.id) {
        if(src.dragCards.length == move.cards.length) {
          valid = true;
          for(var cardIndex in move.cards) {
            var cardId = src.dragCards[cardIndex].id;
            if(move.cards[cardIndex] !== cardId) {
              valid = false;
            }
          }
        }
      }
    }
    return valid;
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
        if((minX >= p.x && minX <= p.x + p.intersectWidth) || (maxX >= p.x && maxX <= p.x + p.intersectWidth)) {
          overlapX = Math.abs(p.x + (p.intersectWidth / 2) - xPoint);
        }
        var overlapY = 0;
        if((minY >= p.y && minY <= p.y + p.intersectHeight) || (maxY >= p.y && maxY <= p.y + p.intersectHeight)) {
          overlapY = Math.abs(p.y + (p.intersectHeight / 2) - yPoint);
        }
        if(overlapX > 0 && overlapY > 0) {
          if(overlapX + overlapY < dropDistance) {
            if(isValidMove(pile, p)) {
              dropDistance = overlapX + overlapY;
              dropTarget = p;
            }
          }
        }
        //console.log("Pile [" + p.id + "] overlaps [" + overlapX + ", " + overlapY + "]");
      }
    }
    //console.log("Choosing [" + (dropTarget === null ? "none" : dropTarget.id) + "] as drop target with distance [" + dropDistance + "].");
    return dropTarget;
  };


  return {
    dragSlice: function(card, p) {
      this.dragCards = this.cards.slice(card.pileIndex);
      for(var c in this.dragCards) {
        this.dragCards[c].startDrag(p);
      }
    },

    endDrag: function() {
      var dropTarget = getDropTarget(this);

      if(dropTarget === null) {
        for(var cancelIndex in this.dragCards) {
          var cancelCard = this.dragCards[cancelIndex];
          cancelCard.dragging = false;
          cancelCard.cancelDrag();
        }
      } else {
        // console.log("Moving [" + this.dragCards + "] to [" + dropTarget.id + "].");
        for(var moveIndex in this.dragCards) {
          var moveCard = this.dragCards[moveIndex];
          moveCard.dragging = false;
        }

        var cardIds = [];
        for(var dragCardIndex in this.dragCards) {
          cardIds.push(this.dragCards[dragCardIndex].id);
        }

        this.game.send("MoveCards", {cards: cardIds, src: this.id, tgt: dropTarget.id});
      }
      this.dragCards = [];
    }
  };
});
