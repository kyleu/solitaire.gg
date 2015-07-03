/* global define:false */
/* global Phaser:false */
/* global _:false */
define(['game/pile/PileLayout', 'game/pile/PileHelpers', 'game/pile/DragFromConstraints'], function (PileLayout, PileHelpers, DragFromConstraints) {
  'use strict';

  function getConstraint(key) {
    var ret = DragFromConstraints[key === undefined ? 'never' : key];
    if(ret === undefined) {
      throw 'Invalid dragFrom constraint [' + key + '].';
    } else {
      return ret;
    }
  }

  function canSelectPile(p) {
    var valid = _.find(p.game.possibleMoves, function(move) {
      return move.moveType === 'select-pile' && move.sourcePile === p.id;
    }) !== undefined;
    return valid;
  }

  function Pile(game, id, pileSet, options) {
    Phaser.Group.call(this, game, game.playmat, id);
    this.id = id;
    this.pileSet = pileSet;
    this.options = options;
    this.cards = [];
    this.game.addPile(this);

    this.empty = new Phaser.Sprite(game, 0, 0, 'empty-piles', 0);
    this.empty.inputEnabled = true;
    this.empty.events.onInputUp.add(function() {
      this.game.options.hidePanels();
      if(canSelectPile(this)) {
        this.pileSelected();
      }
    }, this);
    this.empty.anchor.setTo(0.5, 0.5);
    this.add(this.empty);

    this.canDragFrom = getConstraint(options.dragFromConstraint);

    this.intersectWidth = this.empty.width;
    this.intersectHeight = this.empty.height;
  }

  Pile.prototype = Object.create(Phaser.Group.prototype);
  Pile.prototype.constructor = Pile;

  Pile.prototype.addCard = function(card, cardPileIndex) {
    card.pile = this;
    card.pileIndex = this.cards.length;
    if(cardPileIndex !== undefined) {
      card.pileIndex = cardPileIndex;
    }
    this.cards[card.pileIndex] = card;
    PileLayout.cardAdded(this, card);

    if(this.game.initialized) {
      this.game.playmat.refreshLayout();
    } else {
      this.game.playmat.add(card);
    }
  };

  Pile.prototype.removeCard = function(card) {
    if(card.pile !== this) {
      throw 'Provided card is not a part of this pile.';
    }

    card.pile = null;
    card.pileIndex = 0;

    var index = this.cards.indexOf(card);
    this.cards.splice(index, 1);

    _.each(this.cards, function(c, idx) {
      c.pileIndex = idx;
    });

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
