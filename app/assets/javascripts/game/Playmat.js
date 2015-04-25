define(['game/helpers/Layout'], function (calculateLayout) {
  "use strict";

  var Playmat = function(game, pileSets, layout) {
    Phaser.Group.call(this, game, null, 'playmat');
    this.layout = calculateLayout(pileSets, layout, this.game.world.width / this.game.world.height);

    this.w = this.layout.width * this.game.cardSet.cardWidth;
    this.h = this.layout.height * this.game.cardSet.cardHeight;

    this.resize();

    this.game.add.existing(this);
  };

  Playmat.prototype = Object.create(Phaser.Group.prototype);
  Playmat.prototype.constructor = Playmat;

  Playmat.prototype.addPile = function(pile) {
    var pileLocation = this.layout.locations[pile.id];
    pile.x = this.layout.locations[pile.id].x * this.game.cardSet.cardWidth;
    pile.y = this.layout.locations[pile.id].y * this.game.cardSet.cardHeight;
  };

  Playmat.prototype.resize = function() {
    var widthRatio = this.game.world.width / this.w;
    var heightRatio = this.game.world.height / this.h;
    if(widthRatio < heightRatio) {
      this.scale = new Phaser.Point(widthRatio, widthRatio);
      var yOffset = (this.game.world.height - (this.h * widthRatio)) / 2;
      if(yOffset > 0 || this.y !== 0) {
        this.y = 0;//yOffset;
      }
    } else {
      this.scale = new Phaser.Point(heightRatio, heightRatio);
      var xOffset = (this.game.world.width - (this.w * heightRatio)) / 2;
      if(xOffset > 0 || this.x !== 0) {
        this.x = xOffset;
      }
    }
  };

  return Playmat;
});
