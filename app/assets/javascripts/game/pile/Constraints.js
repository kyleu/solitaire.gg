define(['game/Rank'], function(Rank) {
  return {
    // General
    noOp: function() {
    },
    returnFalse: function() {
      return false;
    },
    returnTrue: function() {
      return true;
    },
    isEmpty: function() {
      return this.cards.length === 0;
    },

    // Select Card
    topCardOnly: function(card) {
      return card.pileIndex == this.cards.length - 1;
    },

    // Drag From
    klondikeTableauDragFrom: function(card) {
      if(!card.faceUp) {
        return false;
      }
      var cards = [];
      for(var selectedIndex = card.pileIndex; selectedIndex < this.cards.length; selectedIndex++) {
        cards.push(this.cards[selectedIndex]);
      }
      var valid = true;
      var lastCard = null;
      for(var c in cards) {
        if(lastCard !== null) {
          if(cards[c].suit.color == lastCard.suit.color) {
            valid = false;
          }
          if(cards[c].rank === Rank.ace || cards[c].rank.value != (lastCard.rank.value - 1)) {
            valid = false;
          }
        }
        lastCard = cards[c];
      }
      return valid;
    },

    // Drag To
    klondikeTableauDragTo: function(pile) {
      if(this.cards.length === 0) {
        return pile.dragCards[0].rank == Rank.king;
      } else {
        var topCard = this.cards[this.cards.length - 1];
        var firstDraggedCard = pile.dragCards[0];
        if(topCard.suit.color == firstDraggedCard.suit.color) {
          return false;
        } else if(topCard.rank === Rank.ace || firstDraggedCard.rank === Rank.king) {
          return false;
        } else {
          return topCard.rank.value == firstDraggedCard.rank.value + 1;
        }
      }
    },

    klondikeFoundationDragTo: function(pile) {
      if(pile.dragCards.length == 1) {
        if(this.cards.length === 0) {
          return pile.dragCards[0].rank === Rank.ace;
        } else {
          var topCard = this.cards[this.cards.length - 1];
          var dragCard = pile.dragCards[0];
          return topCard.suit === dragCard.suit && ((topCard.rank === Rank.ace && dragCard.rank === Rank.two) || topCard.rank.value + 1 == dragCard.rank.value);
        }
      } else {
        return false;
      }
    }
  };
});
