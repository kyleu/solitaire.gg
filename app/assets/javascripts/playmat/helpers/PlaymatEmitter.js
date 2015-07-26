/* global define:false */
/* global Phaser:false */
define(['playmat/helpers/Layout', 'playmat/helpers/PlaymatResizer'], function (calculateLayout, PlaymatResizer) {
  'use strict';

  var PlaymatEmitter = function(playmat) {
    this.playmat = playmat;
    this.suitEmitter = [this.makeEmitter(0), this.makeEmitter(1), this.makeEmitter(2), this.makeEmitter(3)];
  };

  PlaymatEmitter.prototype.makeEmitter = function(frame) {
    var ret = new Phaser.Particles.Arcade.Emitter(this.playmat.game, 0, 0, 50);
    ret.makeParticles('card-suits', frame);
    ret.gravity = 0;
    ret.minParticleSpeed.setTo(-400, -400);
    ret.maxParticleSpeed.setTo(400, 400);
    ret.setAlpha(1, 0, 1000);
    ret.setScale(0.8, 1, 0.8, 1, 1000);
    this.playmat.add(ret);
    return ret;
  };

  PlaymatEmitter.prototype.emitFor = function(card) {
    if(card.suit.index === undefined) {
      console.log('Tried to emit for [' + card + '].');
    } else {
      var e = this.suitEmitter[card.suit.index];
      e.emitX = card.x;
      e.emitY = card.y;
      e.start(true, 1000, null, 40);
    }
  };

  return PlaymatEmitter;
});
