/* global define:false */
define(function () {
  'use strict';

  var GameInput = function(game) {
    this.game = game;
    this.inputVisible = false;
    this.activePile = null;
    this.activeCard = null;
  };

  GameInput.prototype.show = function() {
    if(this.activePile === null) {
      this.setInitialActive();
    }
    if(!this.inputVisible) {
      this.inputVisible = true;
    }
  };

  GameInput.prototype.hide = function() {
    if(this.inputVisible) {
      this.inputVisible = false;
    }
  };

  GameInput.prototype.onPrevCard = function() {
    if (this.activeCard === null) {
      if (this.activePile !== null && this.activePile.cards.length > 0) {
        this.activeCard = this.activePile.cards[0];
      }
    } else {
      if (this.activeCard.pileIndex > 0) {
        this.activeCard = this.activePile.cards[this.activeCard.pileIndex - 2];
      }
    }
  };

  GameInput.prototype.onNextCard = function() {
    if (this.activeCard === null) {
      if (this.activePile !== null && this.activePile.cards.length > 0) {
        this.activeCard = this.activePile.cards[this.activePile.cards.length];
      }
    } else {
      if (this.activeCard.pileIndex < (this.activePile.cards.length - 1)) {
        this.activeCard = this.activePile.cards[this.activeCard.pileIndex];
      }
    }
  };

  GameInput.prototype.onPrevPile = function() {
    if (this.activePile.pileSetIndex === 0) {
      // Previous set
      var prevSetIndex = this.activePile.pileSet.playmatIndex - 1;
      if (prevSetIndex > 0) {
        var prevSet = this.game.pileSets[prevSetIndex];
        this.activePile = prevSet.piles[prevSet.piles.length - 1];
        this.setInitialActive();
      }
    } else {
      // Previous pile
      this.activePile = this.activePile.pileSet.piles[this.activePile.pileSetIndex - 1];
      this.setInitialActive();
    }
  };

  GameInput.prototype.onNextPile = function() {
    if (this.activePile.pileSetIndex === (this.activePile.pileSet.piles.length - 1)) {
      // Next set
      var nextSetIndex = this.activePile.pileSet.playmatIndex;
      if (nextSetIndex < (this.game.pileSets.length - 1)) {
        var nextSet = this.game.pileSets[nextSetIndex];
        this.activePile = nextSet.piles[0];
        this.setInitialActive();
      }
    } else {
      // Next pile
      this.activePile = this.activePile.pileSet.piles[this.activePile.pileSetIndex + 1];
      this.setInitialActive();
    }
  };

  GameInput.prototype.onInput = function(command) {
    if(this.game === undefined) {
      throw new Error('Input not started.');
    }

    if(!this.inputVisible) {
      console.log('InputService activating.');
      this.show();
    } else {
      console.log('InputService received [' + command + '] starting with active pile [' + this.activePile + '], active card [' + this.activeCard + '].');

      switch (command) {
        case 'cancel':
          this.hide();
          break;
        case 'prev-card':
          this.onPrevCard();
          break;
        case 'next-card':
          this.onNextCard();
          break;
        case 'prev-pile':
          this.onPrevPile();
          break;
        case 'next-pile':
          this.onNextPile();
          break;
        case 'select':
          // TODO
          break;
        default:
          console.log('Unknown input command [' + command + '].');
          break;
      }
      console.log('InputService calculated active pile/card:', this.activePile, this.activeCard);
    }
  };

  GameInput.prototype.setInitialActive = function() {
    if(this.activePile === null) {
      var pileSet = this.game.pileSets[this.game.pileSets.length - 1];
      this.activePile = pileSet.piles[pileSet.piles.length / 2];
    }
    if(this.activePile.cards.length === 0) {
      this.activeCard = null;
    } else {
      this.activeCard = this.activePile.cards[this.activePile.cards.length - 1];
    }
  };

  return GameInput;
});
