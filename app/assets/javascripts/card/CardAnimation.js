/* global define:false */
/* global _:false */
define(function() {
  'use strict';

  var driftMax = 20;
  var growthMax = 0.01;

  function win(playmat) {
    var animationDimensions = [ playmat.game.world.width / playmat.scale.x, playmat.game.world.height / playmat.scale.y ];
    var playmatOrigin = [ playmat.x / playmat.scale.x, playmat.y / playmat.scale.y ];

    _.each(playmat.game.cards, function(card) {
      var spin = Math.random() * 3;
      var driftX = (Math.random() * (driftMax * 2)) - driftMax;
      var driftY = (Math.random() * (driftMax * 2)) - driftMax;
      var growthDelta = (Math.random() * (growthMax * 2)) - growthMax;
      card.animation = function() {
        card.angle += spin;
        card.x += driftX;
        card.y += driftY;

        var scaleX = card.scale.x + growthDelta;
        var scaleY = card.scale.y + growthDelta;
        if(scaleX > 1.5 || scaleX < 0.5) {
          growthDelta = -growthDelta;
        }
        card.scale = { x: scaleX, y: scaleY };

        if((card.x + playmatOrigin[0]) < 0 || (card.x + playmatOrigin[0]) > animationDimensions[0]) {
          driftX = -driftX;
        }
        if((card.y + playmatOrigin[1]) < 0 || (card.y + playmatOrigin[1]) > animationDimensions[1]) {
          driftY = -driftY;
        }
      };
    });
  }

  return {
    win: win
  };
});
