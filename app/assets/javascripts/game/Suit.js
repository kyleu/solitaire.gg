define(function () {
  "use strict";

  var hearts = { char: 'H', name: 'Hearts', color: 'red', index: 0 };
  var spades = { char: 'S', name: 'Spades', color: 'black', index: 1 };
  var diamonds = { char: 'D', name: 'Diamonds', color: 'red', index: 2 };
  var clubs = { char: 'C', name: 'Clubs', color: "black", index: 3 };
  var unknown = { char: '?', name: 'Unknown', color: "" };

  return {
    hearts: hearts,
    spades: spades,
    diamonds: diamonds,
    clubs: clubs,
    unknown: unknown,

    all: [hearts, spades, diamonds, clubs],

    fromChar: function(c) {
      switch(c) {
        case 'H': return hearts;
        case 'S': return spades;
        case 'D': return diamonds;
        case 'C': return clubs;
        case '?': return unknown;
        default: throw "Invalid suit [" + c + "].";
      }
    }
  };
});
