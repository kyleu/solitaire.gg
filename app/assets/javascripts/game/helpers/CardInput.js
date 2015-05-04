define(['game/helpers/Tweens'], function (Tweens) {
  "use strict";

  function canSelectCard(card) {
    var valid = _.some(card.game.possibleMoves, function(move) {
      if(move.moveType === "select-card" && move.sourcePile === card.pile.id) {
        if(move.cards.length === 1 && move.cards[0] === card.id) {
          return true;
        }
      }
      return false;
    });
    return valid;
  }

  return {
    startDrag: function(p, dragIndex, card) {
      card.dragging = true;
      card.dragIndex = dragIndex;
      card.inputOriginalPosition = card.position.clone();
      card.anchorPointX = ((p.positionDown.x - card.game.playmat.x) / card.game.playmat.scale.x) - card.x;
      card.anchorPointY = ((p.positionDown.y - card.game.playmat.y) / card.game.playmat.scale.y) - card.y;
      if(card.game.playmat.emitter !== undefined) {
        card.game.playmat.bringToTop(card.game.playmat.emitter);
      }
      card.bringToTop();
    },

    onInputUp: function(e, p, card) {
      if(card.game.playmat.emitter !== undefined) {
        card.game.playmat.emitter.on = false;
      }
      if(card.dragging) {
        card.pile.endDrag();
        if(card.game.playmat.emitter !== undefined) {
          card.game.playmat.bringToTop(card.game.playmat.emitter);
        }
      } else {
        var deltaX = Math.abs(p.positionDown.x - p.positionUp.x);
        var deltaY = Math.abs(p.positionDown.y - p.positionUp.y);
        if(deltaX < 5 && deltaY < 5) {
          if(canSelectCard(card)) {
            card.pile.cardSelected(card);
          }
        }
      }
    },

    cancelDrag: function(card) {
      if(card.inputOriginalPosition !== null) {
        var xDelta = Math.abs(card.x - card.inputOriginalPosition.x);
        var yDelta = Math.abs(card.y - card.inputOriginalPosition.y);
        if(xDelta > 5 || yDelta > 5) {
          // Dragged
          //card.game.add.tween(card).to({x: card.inputOriginalPosition.x, y: card.inputOriginalPosition.y, angle: 0}, 500, Phaser.Easing.Quadratic.InOut, true);
          Tweens.tweenCardTo(card, card.inputOriginalPosition.x, card.inputOriginalPosition.y, 0);
        } else {
          if(canSelectCard(card)) {
            card.pile.cardSelected(card);
          }
        }
        card.dragIndex = null;
        card.actualX = null;
        card.inputOriginalPosition = null;
      } else {
        if(canSelectCard(card)) {
          card.pile.cardSelected(card);
        }
      }
    },

    update: function(card) {
      if(card.animation === null) {
        if(card.dragging) {
          if(card.actualX === undefined || card.actualX === null) {
            card.actualX = card.x;
          }
          var newX = ((card.game.input.x - card.game.playmat.x) / card.game.playmat.scale.x) - card.anchorPointX;
          var xDelta = newX - card.actualX;

          var now = card.game.time.now;
          while(card.inertiaHistory.length > 0 && now - card.inertiaHistory[0][0] > 300) {
            card.inertiaHistory.shift();
          }
          card.inertiaHistory.push([now, xDelta]);

          var totalDelta = 0;
          _.each(card.inertiaHistory, function(inertiaHist) {
            totalDelta += inertiaHist[1];
          });
          var angle = totalDelta / card.inertiaHistory.length / 2;
          var maxAngle = 15 + (3 * card.dragIndex);
          if(angle > maxAngle) {
            angle = maxAngle;
          }
          if(angle < -maxAngle) {
            angle = -maxAngle;
          }
          card.angle = angle;

          card.actualX = newX;

          var swayX = newX - (card.dragIndex * angle * 0.9);
          var newY = ((card.game.input.y - card.game.playmat.y) / card.game.playmat.scale.y) - card.anchorPointY;

          if(swayX !== card.x && newY !== card.y) {
            card.x = swayX;
            card.y = newY;

            if(card.game.playmat.emitter !== undefined) {
              card.game.playmat.emitter.on = true;
              card.game.playmat.emitter.emitX = (card.x);
              card.game.playmat.emitter.emitY = (card.y);
            }
          }
        }
      } else if(card.animation.id === "mouse") {
        var tgtX = card.game.input.x - ((card.width / 2) * card.game.playmat.scale.x);
        var tgtY = card.game.input.y - ((card.height / 2) * card.game.playmat.scale.y);
        var distance = card.game.math.distance(card.world.x, card.world.y, tgtX, tgtY);
        if(distance > 20) {
          var rotation = card.game.math.angleBetween(card.world.x, card.world.y, tgtX, tgtY);
          card.x += Math.cos(rotation) * 10;
          card.y += Math.sin(rotation) * 10;
        }
      }
    }
  };
});
