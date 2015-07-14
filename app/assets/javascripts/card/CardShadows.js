/* global define:false */
/* global _:false */
define([], function() {
  var CardShadows = function(game) {
    this.game = game;

    this.shadow = game.add.sprite(game.world.centerX, game.world.centerY, 'card-blank');
    this.shadow.anchor.set(0.5);
    this.shadow.tint = 0x000000;
    this.shadow.alpha = 0.6;
  };

  CardShadows.prototype.start = function(cards) {
    _.each(cards, function(card, idx) {
      // ???
    });
  };

  return CardShadows;
});
