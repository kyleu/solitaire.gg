define(['game/Rank'], function(Rank) {
  return {
    "never": function() {
      return false;
    },

    "top-card-only": function(cards) {
      var card = cards[0];
      return card.pileIndex == this.cards.length - 1;
    },

    "klondike": function(card) {
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
    }
  };
});
