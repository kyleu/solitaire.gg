define([], function () {
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

  Playmat.prototype.resize = function() {
    var widthRatio = this.game.world.width / this.w;
    this.scale = new Phaser.Point(widthRatio, widthRatio);
  };

  return Playmat;
});
