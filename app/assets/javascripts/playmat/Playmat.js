/* global define:false */
/* global Phaser:false */
/* global _:false */
define([
  'playmat/helpers/Layout', 'playmat/helpers/PlaymatResizer', 'playmat/helpers/PlaymatEmitter'
], function (calculateLayout, PlaymatResizer, PlaymatEmitter) {
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

  Playmat.prototype.win = function() {
    var animationDimensions = [ this.game.world.width / this.scale.x, this.game.world.height / this.scale.y ];
    var playmatOrigin = [ this.x / this.scale.x, this.y / this.scale.y ];
    console.log(animationDimensions, playmatOrigin);

    _.each(this.game.cards, function(card) {
      var origin = [ card.x + playmatOrigin[0], card.y + playmatOrigin[1] ];
      console.log(origin);
    });

    alert('You win!');
  };

  Playmat.prototype.lose = function() {
    alert('You lose!');
  };

  return Playmat;
});
