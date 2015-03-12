define(['game/pile/PileHelpers'], function (PileHelpers) {
  "use strict";

  function redraw(pile) {
    if(pile.cards.length < pile.cardsShown) {
      for(var smallIndex in pile.cards) {
        var smallIndexInt = parseInt(smallIndex);
        var smallX, smallY;
        if(pile.direction === 'd') {
          smallX = pile.x;
          smallY = pile.y + (pile.game.cardSet.cardVerticalOffset * smallIndex);
        } else if(pile.direction === 'r') {
          smallX = pile.x + (pile.game.cardSet.cardHorizontalOffset * smallIndex);
          smallY = pile.y;
        } else {
          throw "Invalid direction [" + pile.direction + "].";
        }
        PileHelpers.tweenCard(pile.cards[smallIndex], smallX, smallY);
      }
    } else {
      for(var largeIndex in pile.cards) {
        var largeIndexInt = parseInt(largeIndex);
        var largeX, largeY;
        if(largeIndexInt === pile.cards.length - 1) {
          if(pile.direction === 'd') {
            largeX = pile.x;
            largeY = pile.y + (pile.game.cardSet.cardVerticalOffset * 2);
          } else if(pile.direction === 'r') {
            largeX = pile.x + (pile.game.cardSet.cardHorizontalOffset * 2);
            largeY = pile.y;
          } else {
            throw "Invalid direction [" + pile.direction + "].";
          }
        } else if(largeIndexInt === pile.cards.length - 2) {
          if(pile.direction === 'd') {
            largeX = pile.x;
            largeY = pile.y + pile.game.cardSet.cardVerticalOffset;
          } else if(pile.direction === 'r') {
            largeX = pile.x + pile.game.cardSet.cardHorizontalOffset;
            largeY = pile.y;
          } else {
            throw "Invalid direction [" + pile.direction + "].";
          }
        } else {
          largeX = pile.x;
          largeY = pile.y;
        }
        PileHelpers.tweenCard(pile.cards[largeIndex], largeX, largeY);
      }
    }

    var additionalWidth = pile.cards.length - 1;
    if(pile.cards.length > pile.cardsShown) {
      additionalWidth = pile.cardsShown - 1;
    }
    pile.intersectWidth = pile.game.cardSet.cardWidth + (additionalWidth * pile.game.cardSet.cardHorizontalOffset);
  }

  return {
    cardAdded: function(pile, card) {
      if(pile.cardsShown === undefined) {
        if(pile.direction === "d") {
          var newY = pile.y + ((pile.cards.length - 1) * pile.game.cardSet.cardVerticalOffset);
          PileHelpers.tweenCard(card, pile.x, newY);
          pile.intersectHeight = pile.game.cardSet.cardHeight + (pile.cards.length === 0 ? 0 : (pile.cards.length - 1) * pile.game.cardSet.cardVerticalOffset);
        } else if(pile.direction === "r") {
          var newX = pile.y + ((pile.cards.length - 1) * pile.game.cardSet.cardHorizontalOffset);
          PileHelpers.tweenCard(card, newX, pile.y);
          pile.intersectWidth = pile.game.cardSet.cardWidth + (pile.cards.length === 0 ? 0 : (pile.cards.length - 1) * pile.game.cardSet.cardHorizontalOffset);
        } else {
          throw "Invalid direction [" + pile.direction + "].";
        }
      } else if(pile.cardsShown == 1) {
        PileHelpers.tweenCard(card, pile.x, pile.y);
      } else {
        redraw(pile);
      }
    },

    cardRemoved: function(pile, card) {
      if(pile.cardsShown === undefined) {
        if(pile.direction === "d") {
          pile.intersectHeight = pile.game.cardSet.cardHeight + (pile.cards.length === 0 ? 0 : (pile.cards.length - 1) * pile.game.cardSet.cardVerticalOffset);
        } else if(pile.direction === "r") {
          pile.intersectWidth = pile.game.cardSet.cardWidth + (pile.cards.length === 0 ? 0 : (pile.cards.length - 1) * pile.game.cardSet.cardHorizontalOffset);
        } else {
          throw "Invalid direction [" + pile.direction + "].";
        }
      } else if(pile.cardsShown == 1) {
        // no op
      } else {
        redraw(pile);
      }
    }
  };
});
