define(function () {
  var Playmat = function(game, layout) {
    Phaser.Group.call(this, game, null, 'playmat');

    this.layout = layout;
    this.w = layout.width;
    this.h = layout.height;

    this.resize();

    this.game.add.existing(this);
  };

  Playmat.prototype = Object.create(Phaser.Group.prototype);
  Playmat.prototype.constructor = Playmat;

  Playmat.prototype.addPile = function(pile) {
    var pileLocation = null;
    for(var pileLocationIndex in this.layout.piles) {
      var pl = this.layout.piles[pileLocationIndex];
      if(pl.id == pile.id) {
        pileLocation = pl;
      }
    }
    pile.x = pileLocation.x;
    pile.y = pileLocation.y;
  };

  Playmat.prototype.resize = function(h, w) {
    var widthRatio = this.game.world.width / this.w;
    var heightRatio = this.game.world.height / this.h;
    if(widthRatio < heightRatio) {
      this.scale = new Phaser.Point(widthRatio, widthRatio);
      var yOffset = (this.game.world.height - (this.h * widthRatio)) / 2;
      if(yOffset > 0 || this.y !== 0) {
        this.y = yOffset;
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
