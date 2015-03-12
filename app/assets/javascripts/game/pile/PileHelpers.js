define(function() {
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
            if(p.canDragTo(pile)) {
              dropDistance = overlapX + overlapY;
              dropTarget = p;
            }
          }
        }
      }
    }
    return dropTarget;
  };


  var PileHelpers = {
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

        this.game.ws.send("MoveCards", {cards: cardIds, src: this.id, tgt: dropTarget.id});
      }
      this.dragCards = [];
    },

    tweenCard: function(card, x, y) {
      if(x != card.x || y != card.y) {
        var tween = card.game.add.tween(card);
        tween.to({x: x, y: y}, 200);
        tween.onComplete.add(function() {
          card.tweening = false;
        }, card);
        card.tweening = true;
        tween.start();
      }
    }
  };

  return PileHelpers;
});
