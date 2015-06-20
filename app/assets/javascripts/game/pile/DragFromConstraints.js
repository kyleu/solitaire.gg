define(['game/Rank', 'game/pile/PileLogic'], function(Rank, PileLogic) {
  "use strict";

  return {
    "never": function() {
      return false;
    },

    "top-card-only": function(card) {
      return card.pileIndex == this.cards.length - 1;
    },

    "sequence": function(card) {
      var opts = card.pile.options.dragFromOptions;
      if(!card.faceUp) {
        return false;
      }
      var cards = [];
      for(var selectedIndex = card.pileIndex; selectedIndex < this.cards.length; selectedIndex++) {
        cards.push(this.cards[selectedIndex]);
      }
      var valid = true;
      var lastCard = null;

      var rankRule = opts.r;
      var suitRule = opts.s;
      var lowRank = opts.lr;

      for(var c in cards) {
        var testCard = cards[c];
        if(lastCard !== null && valid) {
          if (!PileLogic.rank(rankRule, lastCard.rank, testCard.rank, lowRank)) {
            valid = false;
          } else if (!PileLogic.suit(suitRule, lastCard.suit, testCard.suit)) {
            valid = false;
          }
        }
        lastCard = testCard;
      }
      return valid;
    },

    "descending": function(card) {
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
          if(cards[c].suit !== lastCard.suit) {
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

    "piles-empty": function() {
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
