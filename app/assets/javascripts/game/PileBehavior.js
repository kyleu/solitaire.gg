define(function () {
  "use strict";

  var noOp = function() {};

  var tweenCard = function(card, x, y) {
    var tween = card.game.add.tween(card);
    tween.to({ x: x, y: y }, 200);
    tween.start();
  };

  var samePosition = function(pile, card) {
    tweenCard(card, pile.x, pile.y);
  };

  var wasteAdd = function(pile, card) {
    card.y = pile.y;
    if(pile.cards.length === 1) {
      pile.cards[0].x = pile.x;
    } else if(pile.cards.length === 2) {
      pile.cards[0].x = pile.x;
      pile.cards[1].x = pile.x + pile.game.cardSet.cardHorizontalOffset;
    } else {
      for(var cardIndex in pile.cards) {
        var cardIndexInt = parseInt(cardIndex);
        if(cardIndexInt === pile.cards.length - 1) {
          pile.cards[cardIndex].x = pile.x + (pile.game.cardSet.cardHorizontalOffset * 2);
        } else if(cardIndexInt === pile.cards.length - 2) {
          pile.cards[cardIndex].x = pile.x + pile.game.cardSet.cardHorizontalOffset;
        } else {
          pile.cards[cardIndex].x = pile.x;
        }
      }
    }

    var additionalWidth = pile.cards.length - 1;
    if(pile.cards.length > 3) {
      additionalWidth = 2;
    }
    pile.intersectWidth = pile.game.cardSet.cardWidth + (additionalWidth * pile.game.cardSet.cardHorizontalOffset);
  };

  var wasteRemove = function(pile) {
    if(pile.cards.length === 1) {
      pile.cards[0].x = pile.x;
    } else if(pile.cards.length === 2) {
      pile.cards[0].x = pile.x;
      pile.cards[1].x = pile.x + pile.game.cardSet.cardHorizontalOffset;
    } else {
      for(var ci in pile.cards) {
        var cardIndexInt = parseInt(ci);
        if(cardIndexInt === pile.cards.length - 1) {
          pile.cards[ci].x = pile.x + (pile.game.cardSet.cardHorizontalOffset * 2);
        } else if(cardIndexInt === pile.cards.length - 2) {
          pile.cards[ci].x = pile.x + pile.game.cardSet.cardHorizontalOffset;
        } else {
          pile.cards[ci].x = pile.x;
        }
      }
    }

    var additionalWidth = this.cards.length - 1;
    if(pile.cards.length > 3) {
      additionalWidth = 2;
    }
    pile.intersectWidth = pile.game.cardSet.cardWidth + (additionalWidth * pile.game.cardSet.cardHorizontalOffset);
  };

  var tableauAdd = function(pile, card) {
    var tween = card.game.add.tween(card);
    tween.to({
      x: pile.x,
      y: pile.y + ((pile.cards.length - 1) * pile.game.cardSet.cardVerticalOffset)
    }, 200);
    tween.start();
    pile.intersectHeight = pile.game.cardSet.cardHeight + (pile.cards.length === 0 ? 0 : (pile.cards.length - 1) * pile.game.cardSet.cardVerticalOffset);
  };

  var tableauRemove = function(pile) {
    pile.intersectHeight = pile.game.cardSet.cardHeight + (pile.cards.length === 0 ? 0 : (pile.cards.length - 1) * pile.game.cardSet.cardVerticalOffset);
  };

  return {
    stock: {
      cardAdded: samePosition,
      cardRemoved: noOp
    },
    waste: {
      cardAdded: wasteAdd,
      cardRemoved: wasteRemove
    },
    tableau: {
      cardAdded: tableauAdd,
      cardRemoved: tableauRemove
    },
    foundation: {
      cardAdded: samePosition,
      cardRemoved: noOp
    }
  };
});
