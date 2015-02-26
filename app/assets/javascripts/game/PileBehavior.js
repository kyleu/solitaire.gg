define(function () {
  "use strict";

  var noOp = function() {};

  var tweenCard = function(card, x, y) {
    if(x != card.x || y != card.y) {
      var tween = card.game.add.tween(card);
      tween.to({x: x, y: y}, 200);
      tween.start();
    }
  };

  var samePosition = function(pile, card) {
    tweenCard(card, pile.x, pile.y);
  };

  var wasteAdd = function(pile) {
    var y = pile.y;
    if(pile.cards.length === 1) {
      tweenCard(pile.cards[0], pile.x, y);
    } else if(pile.cards.length === 2) {
      tweenCard(pile.cards[0], pile.x, y);
      tweenCard(pile.cards[1], pile.x + pile.game.cardSet.cardHorizontalOffset, y);
    } else {
      for(var cardIndex in pile.cards) {
        var cardIndexInt = parseInt(cardIndex);
        if(cardIndexInt === pile.cards.length - 1) {
          tweenCard(pile.cards[cardIndex], pile.x + (pile.game.cardSet.cardHorizontalOffset * 2), y);
        } else if(cardIndexInt === pile.cards.length - 2) {
          tweenCard(pile.cards[cardIndex], pile.x + pile.game.cardSet.cardHorizontalOffset, y);
        } else {
          tweenCard(pile.cards[cardIndex], pile.x);
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
      tweenCard(pile.cards[0], pile.x, pile.y);
    } else if(pile.cards.length === 2) {
      tweenCard(pile.cards[0], pile.x, pile.y);
      tweenCard(pile.cards[1], pile.x + pile.game.cardSet.cardHorizontalOffset, pile.y);
    } else {
      for(var ci in pile.cards) {
        var cardIndexInt = parseInt(ci);
        if(cardIndexInt === pile.cards.length - 1) {
          tweenCard(pile.cards[ci], pile.x + (pile.game.cardSet.cardHorizontalOffset * 2), pile.y);
        } else if(cardIndexInt === pile.cards.length - 2) {
          tweenCard(pile.cards[ci], pile.x + pile.game.cardSet.cardHorizontalOffset, pile.y);
        } else {
          tweenCard(pile.cards[ci], pile.x, pile.y);
        }
      }
    }

    var additionalWidth = pile.cards.length - 1;
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
