define([], function() {
  "use strict";

  function Backdrop(game) {
    console.log("Backdrop!");
    var lastPosition = [0, 0];
    var bg = new Phaser.Sprite(game, 0, 0);
    bg.width = game.width;
    bg.height = game.height;
    bg.inputEnabled = true;
    bg.events.onInputUp.add(function(e, p) {
      var deltaX = Math.abs(p.positionDown.x - p.positionUp.x);
      var deltaY = Math.abs(p.positionDown.y - p.positionUp.y);
      if(deltaX < 10 && deltaY < 10) {
        game.options.toggleMenus();
        if(game.playmat !== undefined) {
          game.playmat.resize();
        }
      }
    }, this);
    this.bg = bg;
  }

  return Backdrop;
});
