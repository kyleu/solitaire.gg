define(['game/helpers/Layout'], function (calculateLayout) {
  "use strict";

  var Playmat = function(game, pileSets, layoutString) {
    Phaser.Group.call(this, game, null, 'playmat');
    this.game.add.existing(this);

    this.pileSets = pileSets;
    this.layoutString = layoutString;
    this.refreshLayout();

    this.suitEmitter = [];
    this.suitEmitter[0] = this.makeEmitter(1);
    this.suitEmitter[1] = this.makeEmitter(0);
    this.suitEmitter[2] = this.makeEmitter(2);
    this.suitEmitter[3] = this.makeEmitter(3);
  };

  Playmat.prototype = Object.create(Phaser.Group.prototype);
  Playmat.prototype.constructor = Playmat;

  Playmat.prototype.refreshLayout = function() {
    var originalSize = [this.w, this.h];

    this.layout = calculateLayout(this.pileSets, this.layoutString, this.game.world.width / this.game.world.height);

    this.w = this.layout.width * this.game.cardSet.cardWidth;
    this.h = this.layout.height * this.game.cardSet.cardHeight;

    if(this.w !== originalSize[0] || this.h !== originalSize[1]) {
      this.resize();
    }
  };

  Playmat.prototype.makeEmitter = function(frame) {
    var ret = new Phaser.Particles.Arcade.Emitter(this.game, 0, 0, 50);
    ret.makeParticles('suits', frame);
    ret.gravity = 0;
    ret.minParticleSpeed.setTo(-400, -400);
    ret.maxParticleSpeed.setTo(400, 400);
    ret.setAlpha(1, 0, 1000);
    ret.setScale(0.8, 1, 0.8, 1, 1000);
    this.add(ret);
    return ret;
  };

  Playmat.prototype.addPile = function(pile) {
    var pileLocation = this.layout.locations[pile.id];
    if(pileLocation === undefined) {
      throw "Cannot find location for pile [" + pile.id + "].";
    }
    pile.x = pileLocation.x * this.game.cardSet.cardWidth;
    pile.y = pileLocation.y * this.game.cardSet.cardHeight;
  };

  Playmat.prototype.resize = function() {
    var widthRatio = this.game.world.width / this.w;
    var heightRatio = this.game.world.height / this.h;
    if(widthRatio < heightRatio) {
      this.scale = new Phaser.Point(widthRatio, widthRatio);
      var yOffset = (this.game.world.height - (this.h * widthRatio)) / 2;
      if(yOffset > 0 || this.y !== 0) {
        this.y = 0; // yOffset;
      }
    } else {
      this.scale = new Phaser.Point(heightRatio, heightRatio);
      var xOffset = (this.game.world.width - (this.w * heightRatio)) / 2;
      if(xOffset > 0 || this.x !== 0) {
        this.x = xOffset;
      }
    }
  };

  Playmat.prototype.enableTrails = function() {
    this.emitter = new Phaser.Particles.Arcade.Emitter(this.game, 0, 0, 300);
    this.emitter.makeParticles( [ 'fire1', 'fire2', 'fire3', 'smoke' ] );
    this.emitter.gravity = -200;
    this.emitter.setAlpha(1, 0, 3000);
    this.emitter.setScale(1.5, 2, 1.5, 2, 3000);
    this.emitter.start(false, 3000, 5);
    this.emitter.on = false;
    this.add(this.emitter);
  };

  Playmat.prototype.emitFor = function(card) {
    var e = this.suitEmitter[card.suit.index];
    e.emitX = card.x;
    e.emitY = card.y;
    e.start(true, 1000, null, 40);
  };

  return Playmat;
});
