define(function () {
  "use strict";

  function DragGroup(game) {
    Phaser.Group.call(this, game, game.playmat, "drag-group");
  }

  DragGroup.prototype = Object.create(Phaser.Group.prototype);
  DragGroup.prototype.constructor = DragGroup;

  DragGroup.prototype.startDrag = function(cards) {
    console.log("DragGroup.startDrag(" + cards + ")");
  };

  return DragGroup;
});
