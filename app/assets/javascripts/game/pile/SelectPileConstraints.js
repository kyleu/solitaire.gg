define(function() {
  return {
    "never": function() {
      return false;
    },

    "empty": function() {
      return this.cards.length === 0;
    }
  };
});
