define(['game/Rank'], function(Rank) {
  return {
    "never": function() {
      return false;
    },

    "top-card-only": function(card) {
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
    },

    "piles-empty": function(card) {
      var options = this.options.dragFromOptions.piles.split(",");
      for(var pileIndex in this.game.piles) {
        if(options.indexOf(pileIndex) > -1 && pileIndex !== this.id) {
          var pile = this.game.piles[pileIndex];
          if(pile.cards.length > 0) {
            return false;
          }
        }
      }
      return true;
    }
  };
});
