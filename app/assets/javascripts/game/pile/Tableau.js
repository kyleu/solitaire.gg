define(['game/pile/Pile', 'game/pile/PileHelpers'], function(Pile, PileHelpers) {
  "use strict";

  var Tableau = function(game, id) {
    Pile.call(this, game, id, "tableau");
  };

  Tableau.prototype = Object.create(Pile.prototype);
  Tableau.prototype.constructor = Tableau;

  Tableau.prototype.canSelectCard = PileHelpers.returnFalse;
  Tableau.prototype.canSelectPile = PileHelpers.returnFalse;

  Tableau.prototype.canDragFrom = function(card) {
    if(card.pile !== this) {
      throw "Provided card is not part of this pile.";
    }
    var cards = [];

    for(var selectedIndex = card.pileIndex; selectedIndex < this.cards.length; selectedIndex++) {
      cards.push(this.cards[selectedIndex]);
    }

    var valid = true;
    var lastCard = null;
    for(var c in cards) {
      if(lastCard === null) {

      } else {

      }
    }

    return valid;
  };

  Tableau.prototype.canDragTo = function(pile) {
    return false;
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
