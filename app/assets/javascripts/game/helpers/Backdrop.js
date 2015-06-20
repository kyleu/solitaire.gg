define([], function() {
  "use strict";

  var lastClickTime = 0;

  function Backdrop(game) {
    var bg = new Phaser.Sprite(game, 0, 0);
    bg.width = game.width;
    bg.height = game.height;
    bg.inputEnabled = true;
    bg.events.onInputUp.add(function(e, p) {
      var deltaX = Math.abs(p.positionDown.x - p.positionUp.x);
      var deltaY = Math.abs(p.positionDown.y - p.positionUp.y);
      if(deltaX < 10 && deltaY < 10) {
        var now = new Date().getTime();
        if(now - lastClickTime < 1000) {
          game.options.toggleMenus();
          if(game.playmat !== undefined) {
            game.playmat.resize();
          }
          lastClickTime = 0;
        } else {
          lastClickTime = now;
        }
      }
    }, this);
    this.bg = bg;
  }

  return Backdrop;
});
