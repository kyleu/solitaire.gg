define(['game/helpers/Layout'], function (calculateLayout) {
  "use strict";

  var Playmat = function(game, pileSets, layout) {
    Phaser.Group.call(this, game, null, 'playmat');
    this.layout = calculateLayout(pileSets, layout, this.game.world.width / this.game.world.height);

    this.w = this.layout.width * this.game.cardSet.cardWidth;
    this.h = this.layout.height * this.game.cardSet.cardHeight;

    this.resize();

    this.suitEmitter = new Phaser.Particles.Arcade.Emitter(this.game, 0, 0, 400);
    this.suitEmitter.makeParticles('suits', 0);
    this.suitEmitter.gravity = 0;
    this.suitEmitter.minParticleSpeed.setTo(-400, -400);
    this.suitEmitter.maxParticleSpeed.setTo(400, 400);
    this.suitEmitter.setAlpha(1, 0, 1000);
    this.suitEmitter.setScale(0.8, 1, 0.8, 1, 1000);
    this.add(this.suitEmitter);

    this.game.add.existing(this);
  };

  Playmat.prototype = Object.create(Phaser.Group.prototype);
  Playmat.prototype.constructor = Playmat;

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
        this.y = 0; //yOffset;
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
    this.emitter = new Phaser.Particles.Arcade.Emitter(this.game, 0, 0, 400);
    this.emitter.makeParticles( [ 'fire1', 'fire2', 'fire3', 'smoke' ] );
    this.emitter.gravity = 200;
    this.emitter.setAlpha(1, 0, 3000);
    this.emitter.setScale(1.5, 2, 1.5, 2, 3000);
    this.emitter.start(false, 3000, 5);
    this.emitter.on = false;
    this.add(this.emitter);
  };

  Playmat.prototype.emitFor = function(card) {
    this.suitEmitter.x = card.x;
    this.suitEmitter.y = card.y;
    switch(card.suit.index) {
      case 0:
        this.suitEmitter.makeParticles('suits', 1);
        break;
      case 1:
        this.suitEmitter.makeParticles('suits', 0);
        break;
      case 2:
        this.suitEmitter.makeParticles('suits', 2);
        break;
      case 3:
        this.suitEmitter.makeParticles('suits', 3);
        break;
      default:
        throw card.suit;
    }
    this.suitEmitter.start(true, 1000, null, 20);
  };

  return Playmat;
});
