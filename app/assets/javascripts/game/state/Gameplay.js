define(['Config', 'game/Card', 'game/Pile'], function (cfg, Card, Pile) {
  "use strict";

  function Gameplay(game) {
    Phaser.State.call(this, game);
  }

  Gameplay.prototype = Object.create(Phaser.State.prototype);
  Gameplay.prototype.constructor = Gameplay;

  Gameplay.prototype.preload = function() {
    this.game.load.image("bg-texture", "/assets/images/game/bg.jpg");
    this.game.load.image("card-back-medium", "/assets/images/game/cards/medium/BACK.png");
    this.game.load.image("empty-pile-medium", "/assets/images/game/cards/medium/EMPTY.png");
    this.game.load.image("bg-texture", "/assets/images/game/bg.jpg");
    this.game.load.spritesheet('card-medium', 'assets/images/game/cards/medium/ALL.png', 200, 300);
  };

  Gameplay.prototype.create = function() {
    this.bg = this.game.add.tileSprite(0, 0, this.game.width * 2, this.game.height * 2, "bg-texture");
    this.bg.scale = { x: 0.5, y: 0.5 };

    this.game.time.events.loop(Phaser.Timer.SECOND * 2, function() {
      this.ws.send("Ping", { timestamp: new Date().getTime() });
    }, this.game);

    this.game.ws.send("JoinGame", { "name": "Kyle" });
  };

  Gameplay.prototype.update = function() {
    this.game.statusPanel.setFps(this.game.time.fps);
  };

  Gameplay.prototype.onMessage = function(c, v) {
    switch(c) {
      case "GameJoined":
        this.cards = [];
        for(var pileIndex in v.state.piles) {
          var pile = v.state.piles[pileIndex];
          var pileLocation = null;
          for(var pileLocationIndex in v.state.layout.piles) {
            var pl = v.state.layout.piles[pileLocationIndex];
            if(pl.id == pile.id) {
              pileLocation = pl;
            }
          }
          var pileObj = new Pile(this.game, pile.id, pileLocation.x, pileLocation.y);
          console.log(pileObj);
          //var cardObj = new Card(this.game, card.id, card.r, card.s, this.game.world.centerX, this.game.world.centerY);
          //cardObj.inputEnabled = true;
          //cardObj.input.enableDrag(false, true);
          //this.cards[cardIndex] = cardObj;
        }
        break;
      default:
        console.warn("Unhandled message [" + c + "]: " + JSON.stringify(v));
    }
  };

  Gameplay.prototype.resize = function(w, h) {
    console.info("Gameplay resize.");
    this.bg.height = h * 2;
    this.bg.width = w * 2;
  };

  return Gameplay;
});
