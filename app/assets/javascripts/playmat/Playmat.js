/* global define:false */
/* global Phaser:false */
define([
  'playmat/helpers/PlaymatResizer', 'playmat/helpers/PlaymatEmitter',
  'card/CardAnimation', 'ui/GameResults'
], function (PlaymatResizer, PlaymatEmitter, CardAnimation, GameResults) {
  'use strict';

  var Playmat = function(game, pileSets, layoutString) {
    Phaser.Group.call(this, game, null, 'playmat');
    this.game.add.existing(this);

    this.pileSets = pileSets;
    this.layoutString = layoutString;

    this.resizer = new PlaymatResizer(this);
    this.resizer.refreshLayout();

    this.emitter = new PlaymatEmitter(this);
  };

  Playmat.prototype = Object.create(Phaser.Group.prototype);
  Playmat.prototype.constructor = Playmat;

  Playmat.prototype.addPile = function(pile) {
    var pileLocation = this.layout.locations[pile.id];
    if(pileLocation === undefined) {
      throw 'Cannot find location for pile [' + pile.id + '].';
    }
    pile.x = pileLocation.x * this.game.cardSet.cardWidth;
    pile.y = pileLocation.y * this.game.cardSet.cardHeight;
  };

  Playmat.prototype.win = function(msg) {
    this.game.complete = true;

    CardAnimation.win(this);

    if(!GameResults.initialized()) {
      GameResults.init();
    }
    GameResults.show(msg.result, msg.stats, true);
  };

  Playmat.prototype.lose = function() {
    //alert('You lose.');
  };

  return Playmat;
});
