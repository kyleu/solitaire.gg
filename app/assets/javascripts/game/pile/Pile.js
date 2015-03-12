define(['game/pile/PileLayout', 'game/pile/PileHelpers', 'game/pile/SelectCardConstraints', 'game/pile/SelectPileConstraints', 'game/pile/DragFromConstraints', 'game/pile/DragToConstraints'], function (PileLayout, PileHelpers, SelectCardConstraints, SelectPileConstraints, DragFromConstraints, DragToConstraints) {
  "use strict";

  function getConstraint(constraintType, constraints, key) {
    var ret = constraints[key === undefined ? "never" : key];
    if(ret === undefined) {
      throw "Invalid [" + constraintType + "] constraint [" + key + "].";
    } else {
      return ret;
    }
  }

  function Pile(game, id, behavior, options) {
    Phaser.Group.call(this, game, game.playmat, id);
    this.id = id;
    this.behavior = behavior;
    this.options = options;
    console.log(this.id + ": " + this.options);
    this.cards = [];
    this.game.addPile(this);

    this.empty = new Phaser.Sprite(game, 0, 0, 'empty-pile');
    this.empty.inputEnabled = true;
    this.empty.events.onInputUp.add(function() {
      if(this.canSelectPile()) {
        this.pileSelected();
      }
    }, this);
    this.add(this.empty);

    this.canSelectCard = getConstraint("selectCard", SelectCardConstraints, options.selectCardConstraint);
    this.canSelectPile = getConstraint("selectPile", SelectPileConstraints, options.selectPileConstraint);
    this.canDragFrom = getConstraint("dragFrom", DragFromConstraints, options.dragFromConstraint);
    this.canDragTo = getConstraint("dragTo", DragToConstraints, options.dragToConstraint);

    this.intersectWidth = this.empty.width;
    this.intersectHeight = this.empty.height;
  }

  Pile.prototype = Object.create(Phaser.Group.prototype);
  Pile.prototype.constructor = Pile;

  Pile.prototype.addCard = function(card) {
    card.pile = this;
    card.pileIndex = this.cards.length;
    this.cards.push(card);

    PileLayout.cardAdded(this, card);

    this.game.playmat.add(card);
  };

  Pile.prototype.removeCard = function(card) {
    if(card.pile !== this) {
      throw "Provided card is not a part of this pile.";
    }

    card.pile = null;
    card.pileIndex = 0;

    var index = this.cards.indexOf(card);
    this.cards.splice(index, 1);

    for(var cardIndex in this.cards) {
      this.cards[cardIndex].pileIndex = parseInt(cardIndex);
    }

    PileLayout.cardRemoved(this, card);
  };

  Pile.prototype.pileSelected = function(pile, p) {
    this.game.pileSelected(this, p);
  };

  Pile.prototype.cardSelected = function(card) {
    this.game.cardSelected(card);
  };

  Pile.prototype.startDrag = PileHelpers.dragSlice;
  Pile.prototype.endDrag = PileHelpers.endDrag;

  return Pile;
});
