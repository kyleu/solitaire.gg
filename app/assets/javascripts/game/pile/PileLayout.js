define(['game/helpers/Tweens'], function (Tweens) {
  "use strict";

  function redraw(pile) {
    if(pile.cards.length < pile.options.cardsShown) {
      for(var smallIndex in pile.cards) {
        var smallIndexInt = parseInt(smallIndex);
        var smallX, smallY;
        if(pile.options.direction === 'd') {
          smallX = pile.x;
          smallY = pile.y + (pile.game.cardSet.cardVerticalOffset * smallIndexInt);
        } else if(pile.options.direction === 'r') {
          smallX = pile.x + (pile.game.cardSet.cardHorizontalOffset * smallIndexInt);
          smallY = pile.y;
        } else {
          throw "Invalid direction [" + pile.options.direction + "].";
        }
        Tweens.tweenCardTo(pile.cards[smallIndex], smallX, smallY);
      }
    } else {
      for(var largeIndex in pile.cards) {
        var largeIndexInt = parseInt(largeIndex);
        var largeX, largeY;

        var offset = 0;
        if((pile.cards.length - largeIndexInt) < pile.options.cardsShown) {
          if(pile.options.cardsShown === undefined) {
            offset = pile.cards.length - largeIndexInt;
          } else {
            offset = pile.options.cardsShown - (pile.cards.length - largeIndexInt);
          }
        }
        if(pile.options.direction === 'd') {
          largeX = pile.x;
          largeY = pile.y + (pile.game.cardSet.cardVerticalOffset * offset);
        } else if(pile.options.direction === 'r') {
          largeX = pile.x + (pile.game.cardSet.cardHorizontalOffset * offset);
          largeY = pile.y;
        } else {
          throw "Invalid direction [" + pile.options.direction + "].";
        }

        Tweens.tweenCardTo(pile.cards[largeIndex], largeX, largeY);
      }
    }

    var additionalWidth = pile.cards.length - 1;
    if(pile.cards.length > pile.options.cardsShown) {
      additionalWidth = pile.options.cardsShown - 1;
    }
    pile.intersectWidth = pile.game.cardSet.cardWidth + (additionalWidth * pile.game.cardSet.cardHorizontalOffset);
  }

  return {
    cardAdded: function(pile, card) {
      if(pile.behavior === "graveyard") {
        Tweens.tweenRemove(card);
      } else {
        if(pile.options.cardsShown === undefined) {
          if(pile.options.direction === "d") {
            var newY = pile.y + ((pile.cards.length - 1) * pile.game.cardSet.cardVerticalOffset);
            Tweens.tweenCardTo(card, pile.x, newY);
            pile.intersectHeight = pile.game.cardSet.cardHeight + (pile.cards.length === 0 ? 0 : (pile.cards.length - 1) * pile.game.cardSet.cardVerticalOffset);
          } else if(pile.options.direction === "r") {
            var newX = pile.y + ((pile.cards.length - 1) * pile.game.cardSet.cardHorizontalOffset);
            Tweens.tweenCardTo(card, newX, pile.y);
            pile.intersectWidth = pile.game.cardSet.cardWidth + (pile.cards.length === 0 ? 0 : (pile.cards.length - 1) * pile.game.cardSet.cardHorizontalOffset);
          } else {
            throw "Invalid direction [" + pile.options.direction + "].";
          }
        } else if(pile.options.cardsShown == 1) {
          Tweens.tweenCardTo(card, pile.x, pile.y);
        } else {
          redraw(pile);
        }
      }
    },

    cardRemoved: function(pile, card) {
      if(pile.behavior === "graveyard") {
        Tweens.tweenRestore(card);
      } else {
        if(pile.options.cardsShown === undefined) {
          if(pile.options.direction === "d") {
            pile.intersectHeight = pile.game.cardSet.cardHeight + (pile.cards.length === 0 ? 0 : (pile.cards.length - 1) * pile.game.cardSet.cardVerticalOffset);
          } else if(pile.options.direction === "r") {
            pile.intersectWidth = pile.game.cardSet.cardWidth + (pile.cards.length === 0 ? 0 : (pile.cards.length - 1) * pile.game.cardSet.cardHorizontalOffset);
          } else {
            throw "Invalid direction [" + pile.options.direction + "].";
          }
        } else if(pile.options.cardsShown == 1) {
          // no op
        } else {
          redraw(pile);
        }
      }
    }
  };
});
