define(function () {
  "use strict";

  var two = { value: 2, char: "2", name: "Two" };
  var three = { value: 3, char: "3", name: "Three" };
  var four = { value: 4, char: "4", name: "Four" };
  var five = { value: 5, char: "5", name: "Five" };
  var six = { value: 6, char: "6", name: "Six" };
  var seven = { value: 7, char: "7", name: "Seven" };
  var eight = { value: 8, char: "8", name: "Eight" };
  var nine = { value: 9, char: "9", name: "Nine" };
  var ten = { value: 10, char: "X", name: "Ten" };
  var jack = { value: 11, char: "J", name: "Jack" };
  var queen = { value: 12, char: "Q", name: "Queen" };
  var king = { value: 13, char: "K", name: "King" };
  var ace = { value: 14, char: "A", name: "Ace" };
  var unknown = { value: 0, char: "?", name: "Unknown" };

  var Rank = {
    two: two,
    three: three,
    four: four,
    five: five,
    six: six,
    seven: seven,
    eight: eight,
    nine: nine,
    ten: ten,
    jack: jack,
    queen: queen,
    king: king,
    ace: ace,
    unknown: unknown,

    all: [two, three, four, five, six, seven, eight, nine, ten, jack, queen, king, ace],
    fromChar: function(c) {
      switch(c) {
        case '2': return two;
        case '3': return three;
        case '4': return four;
        case '5': return five;
        case '6': return six;
        case '7': return seven;
        case '8': return eight;
        case '9': return nine;
        case 'X': return ten;
        case 'J': return jack;
        case 'Q': return queen;
        case 'K': return king;
        case 'A': return ace;
        case '?': return unknown;
        default: throw "Invalid rank [" + c + "].";
      }
    }
  };

  return Rank;
});
