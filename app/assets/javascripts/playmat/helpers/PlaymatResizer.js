/* global define:false */
/* global Phaser:false */
define(['playmat/helpers/Layout'], function (calculateLayout) {
  'use strict';

  var PlaymatResizer = function(playmat) {
    this.playmat = playmat;
  };

  PlaymatResizer.prototype.refreshLayout = function() {
    var p = this.playmat;
    var originalSize = [p.w, p.h];

    p.layout = calculateLayout(p.pileSets, p.layoutString, p.game.world.width / p.game.world.height, p.game.menusVisible);

    p.w = p.layout.width * p.game.cardSet.cardWidth;
    p.h = (p.layout.height - 0.1) * p.game.cardSet.cardHeight;

    if(p.w !== originalSize[0] || p.h !== originalSize[1]) {
      this.resize();
    }
  };

  PlaymatResizer.prototype.resize = function() {
    var p = this.playmat;
    var totalHeight = p.game.world.height;
    var menuHeight = 0;
    if(p.game.menusVisible) {
      totalHeight -= 80;
      menuHeight = 40;
    }
    var widthRatio = p.game.world.width / p.w;
    var heightRatio = totalHeight / p.h;

    var newPosition = p.position;
    var newScale = p.scale;

    if(widthRatio < heightRatio) {
      newScale = new Phaser.Point(widthRatio, widthRatio);
      var yOffset = (totalHeight - (p.h * widthRatio)) / 2;
      if(yOffset > 0 || p.y !== menuHeight) {
        newPosition = new Phaser.Point(0, menuHeight /* yOffset */);
      }
    } else {
      newScale = new Phaser.Point(heightRatio, heightRatio);
      var xOffset = (p.game.world.width - (p.w * heightRatio)) / 2;
      if(xOffset > 0 || p.x !== 0) {
        newPosition = new Phaser.Point(xOffset, menuHeight);
      }
    }
    if(p.game.initialized) {
      p.game.add.tween(p.scale).to({x: newScale.x, y: newScale.y}, 500, Phaser.Easing.Quadratic.InOut, true);
      p.game.add.tween(p.position).to({x: newPosition.x, y: newPosition.y}, 500, Phaser.Easing.Quadratic.InOut, true);
    } else {
      p.scale = newScale;
      p.position = newPosition;
    }
  };

  return PlaymatResizer;
});
