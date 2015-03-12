define(['game/Rank'], function(Rank) {
  return {
    "never": function() {
      return false;
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
    }
  };
});
