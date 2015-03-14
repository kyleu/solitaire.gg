define(['game/Rank'], function(Rank) {
  return {
    "never": function() {
      return false;
    },

    "empty": function() {
      return this.cards.length === 0;
    },

    "klondike": function(pile) {
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

    "foundation": function(pile) {
      if(pile.dragCards.length === 1) {
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
    },

    "same-rank": function(pile) {
      if(this.cards.length === 0) {
        return false;
      } else {
        var topCardRank = this.cards[this.cards.length - 1].rank.value;
        var draggedCardRank = pile.dragCards[0].rank.value;
        return topCardRank === draggedCardRank;
      }
    },

    "lower-rank": function(pile) {
      var draggedCardRank = pile.dragCards[0].rank.value;
      if(this.cards.length === 0) {
        return false;
      } else {
        var topCardRank = this.cards[this.cards.length - 1].rank.value;
        if(topCardRank == 2) {
          return draggedCardRank === 14;
        } else {
          console.log(topCardRank + ":" + draggedCardRank);
          return topCardRank === draggedCardRank + 1;
        }
      }
    },

    "alternating-rank": function(pile) {
      if(pile.dragCards.length !== 1) {
        return false;
      } else {
        var topCardRank = this.cards[this.cards.length - 1].rank.value;
        var draggedCardRank = pile.dragCards[0].rank.value;
        if(topCardRank == 13 /* King */) {
          return topCardRank === draggedCardRank + 1;
        } else if(topCardRank == 14 /* Ace */) {
          return draggedCardRank === 2;
        } else if(topCardRank == 2 /* Two */) {
          return draggedCardRank == 14 || draggedCardRank === 3;
        } else {
          return topCardRank === draggedCardRank + 1 || topCardRank === draggedCardRank - 1;
        }
      }
    }
  };
});
