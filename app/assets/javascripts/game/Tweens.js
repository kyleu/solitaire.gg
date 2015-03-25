define([], function() {
  return {
    tweenCardTo: function(card, x, y) {
      if(x != card.x || y != card.y) {
        var tween = card.game.add.tween(card);
        tween.to({ x: x, y: y }, 200);
        tween.onComplete.add(function() {
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
        card.destroy();
      }, card);
      card.tweening = true;
      tween.start();
    },

    tweenFlip: function(card) {
      var tween = card.game.add.tween(card);
      tween.to({ width: 0 }, 400);
      tween.onComplete.add(function() {
        card.tweening = false;
      }, card);
      card.tweening = true;
      tween.start();
    }
  };
});
