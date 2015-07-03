/* global define:false */
/* global _:false */
define(['game/helpers/Tweens'], function (Tweens) {
  'use strict';

  function redraw(pile) {
    if(pile.cards.length < pile.options.cardsShown) {
      _.each(pile.cards, function(smallCard, smallIndex) {
        var smallX, smallY;
        if(pile.options.direction === 'd') {
          smallX = pile.x;
          smallY = pile.y + (pile.game.cardSet.cardVerticalOffset * smallIndex);
        } else if(pile.options.direction === 'r') {
          smallX = pile.x + (pile.game.cardSet.cardHorizontalOffset * smallIndex);
          smallY = pile.y;
        } else {
          throw 'Invalid direction [' + pile.options.direction + '].';
        }
        Tweens.tweenCardTo(smallCard, smallX, smallY, 0);
      });
    } else {
      _.each(pile.cards, function(largeCard, largeIndex) {
        var largeX, largeY;

        var offset = 0;
        if((pile.cards.length - largeIndex) < pile.options.cardsShown) {
          if(pile.options.cardsShown === undefined || pile.options.cardsShown === null) {
            offset = pile.cards.length - largeIndex;
          } else {
            offset = pile.options.cardsShown - (pile.cards.length - largeIndex);
          }
        }
        if(pile.options.direction === 'd') {
          largeX = pile.x;
          largeY = pile.y + (pile.game.cardSet.cardVerticalOffset * offset);
        } else if(pile.options.direction === 'r') {
          largeX = pile.x + (pile.game.cardSet.cardHorizontalOffset * offset);
          largeY = pile.y;
        } else {
          throw 'Invalid direction [' + pile.options.direction + '].';
        }

        Tweens.tweenCardTo(largeCard, largeX, largeY, 0);
      });
    }

    var additionalWidth = pile.cards.length - 1;
    if(pile.cards.length > pile.options.cardsShown) {
      additionalWidth = pile.options.cardsShown - 1;
    }
    pile.intersectWidth = pile.game.cardSet.cardWidth + (additionalWidth * pile.game.cardSet.cardHorizontalOffset);
  }

  return {
    cardAdded: function(pile, card) {
      if(pile.x < 0 || pile.y < 0) {
        Tweens.tweenRemove(card, true);
        card.game.playmat.emitFor(card);
      } else {
        var emitFor = pile.pileSet.behavior === 'foundation';
        if(pile.options.cardsShown === undefined || pile.options.cardsShown === null) {
          var offsetCount = pile.cards.length === 0 ? 0 : (pile.cards.length - 1);
          if(pile.options.direction === 'd') {
            var newY = pile.y + (offsetCount * pile.game.cardSet.cardVerticalOffset);
            Tweens.tweenCardTo(card, pile.x, newY, 0, emitFor);
            pile.intersectHeight = pile.game.cardSet.cardHeight + (offsetCount * pile.game.cardSet.cardVerticalOffset);
          } else if(pile.options.direction === 'r') {
            var newX = pile.y + ((pile.cards.length - 1) * pile.game.cardSet.cardHorizontalOffset);
            Tweens.tweenCardTo(card, newX, pile.y, 0, emitFor);
            pile.intersectWidth = pile.game.cardSet.cardWidth + (offsetCount * pile.game.cardSet.cardHorizontalOffset);
          } else {
            throw 'Invalid direction [' + pile.options.direction + '].';
          }
        } else if(pile.options.cardsShown === 1) {
          Tweens.tweenCardTo(card, pile.x, pile.y, 0, emitFor);
        } else {
          redraw(pile);
        }
      }
    },

    cardRemoved: function(pile, card) {
      if(pile.x < 0 || pile.y < 0) {
        Tweens.tweenRestore(card);
      } else {
        if(pile.options.cardsShown === undefined || pile.options.cardsShown === null) {
          var offsetCount = pile.cards.length === 0 ? 0 : (pile.cards.length - 1);
          if(pile.options.direction === 'd') {
            pile.intersectHeight = pile.game.cardSet.cardHeight + (offsetCount * pile.game.cardSet.cardVerticalOffset);
          } else if(pile.options.direction === 'r') {
            pile.intersectWidth = pile.game.cardSet.cardWidth + (offsetCount * pile.game.cardSet.cardHorizontalOffset);
          } else {
            throw 'Invalid direction [' + pile.options.direction + '].';
          }
        } else if(pile.options.cardsShown !== 1) {
          redraw(pile);
        }
      }
    }
  };
});
