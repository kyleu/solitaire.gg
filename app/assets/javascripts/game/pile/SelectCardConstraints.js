define(function() {
  return {
    "never": function() {
      return false;
    },

    "top-card-only": function(card) {
      return card.pileIndex === this.cards.length - 1;
    }
  };
});
