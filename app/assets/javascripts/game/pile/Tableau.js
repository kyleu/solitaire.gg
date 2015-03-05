define(['game/Rank', 'game/pile/Pile', 'game/pile/PileHelpers'], function(Rank, Pile, PileHelpers) {
  "use strict";

  var Tableau = function(game, id) {
    Pile.call(this, game, id, "tableau");
  };

  Tableau.prototype = Object.create(Pile.prototype);
  Tableau.prototype.constructor = Tableau;

  Tableau.prototype.canSelectCard = function(card) {
    return card == this.cards[this.cards.length - 1];
  };
  Tableau.prototype.canSelectPile = PileHelpers.returnFalse;

  Tableau.prototype.canDragFrom = function(card) {
    if(card.pile !== this) {
      throw "Provided card is not part of this pile.";
    }

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
  };

  Tableau.prototype.canDragTo = function(pile) {
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
  };

  Tableau.prototype.startDrag = PileHelpers.dragSlice;

  Tableau.prototype.endDrag = PileHelpers.endDrag;

  Tableau.prototype.cardAdded = function(card) {
    var tween = card.game.add.tween(card);
    tween.to({
      x: this.x,
      y: this.y + ((this.cards.length - 1) * this.game.cardSet.cardVerticalOffset)
    }, 200);
    tween.start();
    this.intersectHeight = this.game.cardSet.cardHeight + (this.cards.length === 0 ? 0 : (this.cards.length - 1) * this.game.cardSet.cardVerticalOffset);
  };

  Tableau.prototype.cardRemoved = function() {
    this.intersectHeight = this.game.cardSet.cardHeight + (this.cards.length === 0 ? 0 : (this.cards.length - 1) * this.game.cardSet.cardVerticalOffset);
  };

  return Tableau;
});
