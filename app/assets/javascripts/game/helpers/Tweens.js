define([], function() {
  "use strict";

  return {
    tweenCardTo: function(card, x, y, angle) {
      if(x != card.x || y != card.y) {
        var tween = card.game.add.tween(card);
        tween.to({ x: x, y: y, angle: angle }, 500, Phaser.Easing.Cubic.Out);
        tween.onComplete.add(function() {
          card.actualX = x;
          card.tweening = false;
        }, card);
        card.tweening = true;
        tween.start();
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
