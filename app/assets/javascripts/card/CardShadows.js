/* global define:false */
/* global _:false */
define([], function() {
  var CardShadows = function(playmat) {
    this.game = playmat.game;

    var shadows = [];
    _.each([0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12], function() {
      var shadow = playmat.game.add.sprite(0, 0, 'card-blank');
      shadow.anchor.set(0.5);
      shadow.tint = 0x000000;
      shadow.alpha = 0.2;
      shadow.visible = false;
      playmat.add(shadow);
      shadows.push(shadow);
    });
    this.shadows = shadows;
  };

  CardShadows.prototype.start = function(cards) {
    this.cards = cards;
    var shadows = this.shadows;
    _.each(cards, function(card, idx) {
      var shadow = shadows[idx];
      shadow.bringToTop();
      shadow.x = card.x + 30;
      shadow.y = card.y + 30;
      shadow.visible = true;
    });
    _.each(cards, function(card) {
      card.bringToTop();
    });
  };

  CardShadows.prototype.update = function() {
    var shadows = this.shadows;
    _.each(this.cards, function(card, idx) {
      var shadow = shadows[idx];
      shadow.x = card.x + 30;
      shadow.y = card.y + 30;
      shadow.angle = card.angle;
    });
  };

  CardShadows.prototype.stop = function() {
    _.each(this.shadows, function(shadow) {
      shadow.visible = false;
    });
  };

  return CardShadows;
});
