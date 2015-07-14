/* global define:false */
/* global _:false */
define(function() {
  'use strict';

  var isValidMove = function(src, tgt) {
    function check(c, idx) {
      if(c !== src.dragCards[idx].id) {
        valid = false;
      }
    }
    var valid = false;
    _.each(src.game.possibleMoves, function(move) {
      if(move.moveType === 'move-cards' && move.sourcePile === src.id && move.targetPile === tgt.id) {
        if(src.dragCards.length === move.cards.length) {
          valid = true;
          _.each(move.cards, check);
        }
      }
    });
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

    _.each(pile.game.piles, function(p) {
      if(p.id !== pile.id) {
        var overlapX = 0;
        if((minX >= p.x && minX <= p.x + p.intersectWidth) || (maxX >= p.x && maxX <= p.x + p.intersectWidth)) {
          overlapX = Math.abs(p.x - xPoint);
        }
        var overlapY = 0;
        if((minY >= p.y && minY <= p.y + p.intersectHeight) || (maxY >= p.y && maxY <= p.y + p.intersectHeight)) {
          overlapY = Math.abs(p.y - yPoint);
        }
        if((overlapX > 0 && overlapY > 0) && (overlapX + overlapY < dropDistance) && isValidMove(pile, p)) {
          dropDistance = overlapX + overlapY;
          dropTarget = p;
        }
        //console.log('Pile [' + p.id + '] overlaps [' + overlapX + ', ' + overlapY + ']');
      }
    });
    //console.log('Choosing [' + (dropTarget === null ? 'none' : dropTarget.id) + '] as drop target with distance [' + dropDistance + '].');
    return dropTarget;
  };

  return {
    dragSlice: function(card, p) {
      this.dragCards = this.cards.slice(card.pileIndex);
      _.each(this.dragCards, function(c, idx) {
        c.startDrag(p, idx);
      });
    },

    endDrag: function() {
      var dropTarget = getDropTarget(this);

      if(dropTarget === null) {
        _.each(this.dragCards, function(cancelCard) {
          cancelCard.dragging = false;
          cancelCard.cancelDrag();
        });
      } else {
        // console.log('Moving [' + this.dragCards + '] to [' + dropTarget.id + '].');
        _.each(this.dragCards, function(moveCard) {
          moveCard.dragging = false;
        });

        var cardIds = [];
        _.each(this.dragCards, function(dragCard) {
          cardIds.push(dragCard.id);
        });

        this.game.sendMove({ moveType: 'move-cards', cards: cardIds, sourcePile: this.id, targetPile: dropTarget.id });
      }
      this.dragCards = [];
    }
  };
});
