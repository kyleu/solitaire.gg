define([], function() {
  "use strict";

  return {
    tweenCardTo: function(card, x, y, angle, emitWhenComplete) {
      var time = 500;
      if(x != card.x || y != card.y) {
        var xTween = card.game.add.tween(card);
        xTween.to({ x: x, angle: angle }, time, Phaser.Easing.Cubic.Out);
        xTween.onComplete.add(function() {
          card.actualX = x;
          card.tweening = false;
          if(emitWhenComplete) {
            card.game.playmat.emitFor(card);
          }
        }, card);
        xTween.start();

        var targetY = Math.min(y, card.y) - 25;
        var yTween = card.game.add.tween(card);
        yTween.to({y: targetY}, time * 0.5, Phaser.Easing.Cubic.Out);
        yTween.to({y: y}, time * 0.5, Phaser.Easing.Cubic.In);
        yTween.start();

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
      hideTween.to({ width: origWidth / 5 }, 100, Phaser.Easing.Circular.InOut);
      hideTween.onComplete.add(function() {
        card.updateSprite(faceUp);
        var showTween = card.game.add.tween(card);
        showTween.to({ width: origWidth }, 100, Phaser.Easing.Circular.InOut);
        showTween.onComplete.add(function() {
          card.tweening = false;
        }, card);
        showTween.start();
      }, card);
      card.tweening = true;
      hideTween.start();
    }
  };
});
