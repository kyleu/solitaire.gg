define(function () {
  "use strict";

  var PileBehavior = {
    stock: {
      cardAdded: function(pile, card) {
        card.y = pile.y;
        card.x = pile.x;
      },
      cardRemoved: function(pile, card) {

      }
    },
    waste: {
      cardAdded: function(pile, card) {
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
      },
      cardRemoved: function(pile, card) {
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
      }
    },
    tableau: {
      cardAdded: function(pile, card) {
        card.y = pile.y + ((pile.cards.length - 1) * pile.game.cardSet.cardVerticalOffset);
        card.x = pile.x;
        pile.intersectHeight = pile.game.cardSet.cardHeight + (pile.cards.length === 0 ? 0 : (pile.cards.length - 1) * pile.game.cardSet.cardVerticalOffset);
      },
      cardRemoved: function(pile, card) {
        pile.intersectHeight = pile.game.cardSet.cardHeight + (pile.cards.length === 0 ? 0 : (pile.cards.length - 1) * pile.game.cardSet.cardVerticalOffset);
      }
    },
    foundation: {
      cardAdded: function(pile, card) {
        card.y = pile.y;
        card.x = pile.x;
      },
      cardRemoved: function(pile, card) {

      }
    }
  };

  return PileBehavior;
});
