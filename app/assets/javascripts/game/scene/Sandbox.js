define(['Config', 'game/Card'], function (cfg, Card) {
  "use strict";

  function Sandbox(game) {
    Phaser.State.call(this, game);
  }

  Sandbox.prototype = Object.create(Phaser.State.prototype);
  Sandbox.prototype.constructor = Sandbox;

  Sandbox.prototype.preload = function() {
    this.game.physics.startSystem(Phaser.Physics.ARCADE);

    this.game.load.image("bg-texture", "/assets/images/game/bg.jpg");

    this.game.load.image('2H', 'assets/images/game/cards/medium/H2.png');
    this.game.load.image('3H', 'assets/images/game/cards/medium/H3.png');
    this.game.load.image('4H', 'assets/images/game/cards/medium/H4.png');
    this.game.load.image('5H', 'assets/images/game/cards/medium/H5.png');
    this.game.load.image('6H', 'assets/images/game/cards/medium/H6.png');
    this.game.load.image('7H', 'assets/images/game/cards/medium/H7.png');
    this.game.load.image('8H', 'assets/images/game/cards/medium/H8.png');
    this.game.load.image('9H', 'assets/images/game/cards/medium/H9.png');
    this.game.load.image('XH', 'assets/images/game/cards/medium/H10.png');
    this.game.load.image('JH', 'assets/images/game/cards/medium/H11.png');
    this.game.load.image('QH', 'assets/images/game/cards/medium/H12.png');
    this.game.load.image('KH', 'assets/images/game/cards/medium/H13.png');
    this.game.load.image('AH', 'assets/images/game/cards/medium/H14.png');

    this.game.load.image('2S', 'assets/images/game/cards/medium/S2.png');
    this.game.load.image('3S', 'assets/images/game/cards/medium/S3.png');
    this.game.load.image('4S', 'assets/images/game/cards/medium/S4.png');
    this.game.load.image('5S', 'assets/images/game/cards/medium/S5.png');
    this.game.load.image('6S', 'assets/images/game/cards/medium/S6.png');
    this.game.load.image('7S', 'assets/images/game/cards/medium/S7.png');
    this.game.load.image('8S', 'assets/images/game/cards/medium/S8.png');
    this.game.load.image('9S', 'assets/images/game/cards/medium/S9.png');
    this.game.load.image('XS', 'assets/images/game/cards/medium/S10.png');
    this.game.load.image('JS', 'assets/images/game/cards/medium/S11.png');
    this.game.load.image('QS', 'assets/images/game/cards/medium/S12.png');
    this.game.load.image('KS', 'assets/images/game/cards/medium/S13.png');
    this.game.load.image('AS', 'assets/images/game/cards/medium/S14.png');

    this.game.load.image('2D', 'assets/images/game/cards/medium/D2.png');
    this.game.load.image('3D', 'assets/images/game/cards/medium/D3.png');
    this.game.load.image('4D', 'assets/images/game/cards/medium/D4.png');
    this.game.load.image('5D', 'assets/images/game/cards/medium/D5.png');
    this.game.load.image('6D', 'assets/images/game/cards/medium/D6.png');
    this.game.load.image('7D', 'assets/images/game/cards/medium/D7.png');
    this.game.load.image('8D', 'assets/images/game/cards/medium/D8.png');
    this.game.load.image('9D', 'assets/images/game/cards/medium/D9.png');
    this.game.load.image('XD', 'assets/images/game/cards/medium/D10.png');
    this.game.load.image('JD', 'assets/images/game/cards/medium/D11.png');
    this.game.load.image('QD', 'assets/images/game/cards/medium/D12.png');
    this.game.load.image('KD', 'assets/images/game/cards/medium/D13.png');
    this.game.load.image('AD', 'assets/images/game/cards/medium/D14.png');

    this.game.load.image('2C', 'assets/images/game/cards/medium/C2.png');
    this.game.load.image('3C', 'assets/images/game/cards/medium/C3.png');
    this.game.load.image('4C', 'assets/images/game/cards/medium/C4.png');
    this.game.load.image('5C', 'assets/images/game/cards/medium/C5.png');
    this.game.load.image('6C', 'assets/images/game/cards/medium/C6.png');
    this.game.load.image('7C', 'assets/images/game/cards/medium/C7.png');
    this.game.load.image('8C', 'assets/images/game/cards/medium/C8.png');
    this.game.load.image('9C', 'assets/images/game/cards/medium/C9.png');
    this.game.load.image('XC', 'assets/images/game/cards/medium/C10.png');
    this.game.load.image('JC', 'assets/images/game/cards/medium/C11.png');
    this.game.load.image('QC', 'assets/images/game/cards/medium/C12.png');
    this.game.load.image('KC', 'assets/images/game/cards/medium/C13.png');
    this.game.load.image('AC', 'assets/images/game/cards/medium/C14.png');
  };

  Sandbox.prototype.create = function() {
    var bg = this.game.add.tileSprite(0, 0, this.game.width * 2, this.game.height * 2, "bg-texture");
    bg.scale = { x: 0.5, y: 0.5 };

    this.game.ws.send("JoinGame", { "name": "Kyle" });
  };

  Sandbox.prototype.update = function() {
  };

  Sandbox.prototype.onMessage = function(c, v) {
    switch(c) {
      case "GameJoined":
        this.cards = [];
        for(var cardIndex in v.deck.cards) {
          var card = v.deck.cards[cardIndex];
          this.cards[cardIndex] = new Card(cardIndex, card.id, card.r, card.s, this.game, this.game.world.centerX, this.game.world.centerY);
        }
        break;
      default:
        cfg.log.warn("Unhandled message [" + c + "]: " + JSON.stringify(v));
    }
  };

  Sandbox.prototype.resize = function(h, w) {
    cfg.log.info("Sandbox resize");
  };

  return Sandbox;
});
