/* global define:false */
/* global Phaser:false */
define([], function() {
  'use strict';

  return {
    tweenCardTo: function(card, x, y, angle, emitWhenComplete) {
      var time = 500;
      if(x !== card.x || y !== card.y) {
        var xTween = card.game.add.tween(card);
        xTween.to({ x: x, angle: angle }, time, Phaser.Easing.Cubic.Out);
        xTween.onComplete.add(function() {
          card.actualX = x;
          card.tweening = false;
          if(emitWhenComplete) {
            card.game.playmat.emitter.emitFor(card);
          }
        }, card);
        xTween.start();

        var bounce = (y === card.y) && (Math.abs(card.x - x) > card.width);

        if(bounce) {
          var targetY = y - (card.height * 0.05);
          var yTween = card.game.add.tween(card);
          yTween.to({y: targetY}, time * 0.5, Phaser.Easing.Cubic.Out);
          yTween.to({y: y}, time * 0.5, Phaser.Easing.Cubic.In);
          yTween.start();
        } else {
          card.game.add.tween(card).to({y: y}, time, Phaser.Easing.Cubic.Out).start();
        }

        card.tweening = true;
      }
    },

    tweenRemove: function(card) {
      var tween = card.game.add.tween(card);
      tween.to({ alpha: 0 }, 400);
      tween.onComplete.add(function() {
        card.game.playmat.remove(card);
      }, card);
      card.tweening = true;
      tween.start();
    },

    tweenRestore: function(card) {
      card.game.playmat.add(card);
      var tween = card.game.add.tween(card);
      tween.to({ alpha: 1 }, 400);
      tween.onComplete.add(function() {
        //card.destroy();
      }, card);
      card.tweening = true;
      tween.start();
    },

    tweenFlip: function(card, faceUp) {
      var origWidth = card.width;
      var hideTween = card.game.add.tween(card);
      hideTween.to({ width: origWidth / 5 }, 100, Phaser.Easing.Cubic.Out);
      hideTween.onComplete.add(function() {
        card.updateSprite(faceUp);
        var showTween = card.game.add.tween(card);
        showTween.to({ width: origWidth }, 100, Phaser.Easing.Cubic.In);
        showTween.onComplete.add(function() {
          card.tweening = false;
          card.width = origWidth;
        }, card);
        showTween.start();
      }, card);
      hideTween.start();

      card.tweening = true;
    }
  };
});
