define([
  'utils/Config', 'game/Rank', 'game/Suit', 'game/Card', 'game/pile/Pile',
  'game/helpers/Display', 'game/helpers/Keyboard', 'game/helpers/Tweens',
  'game/Playmat', 'game/state/GameState'
], function (cfg, Rank, Suit, Card, Pile, Display, Keyboard, Tweens, Playmat, GameState) {
  "use strict";

  function Gameplay(game) {
    GameState.call(this, 'gameplay', game);
    this.assetRoot = "/";
    if(cfg.assetRoot !== undefined) {
      this.assetRoot = cfg.assetRoot;
    }
  }

  Gameplay.prototype = Object.create(GameState.prototype);
  Gameplay.prototype.constructor = Gameplay;

  Gameplay.prototype.preload = function() {
    this.game.stage.disableVisibilityChange = true;
    var imageKey = this.game.cardSet.key;
    this.game.load.image('bg-texture', this.assetRoot + 'assets/images/game/bg.jpg');
    this.game.load.image('card-back', this.assetRoot + 'assets/images/game/cards/' + imageKey + '/BACK.png');
    this.game.load.image('empty-pile', this.assetRoot + 'assets/images/game/cards/' + imageKey + '/EMPTY.png');
    this.game.load.spritesheet(
      'card', this.assetRoot + 'assets/images/game/cards/' + imageKey + '/ALL.png', this.game.cardSet.cardWidth, this.game.cardSet.cardHeight
    );

    this.game.load.image('fire1', this.assetRoot + 'assets/images/particles/fire1.png');
    this.game.load.image('fire2', this.assetRoot + 'assets/images/particles/fire2.png');
    this.game.load.image('fire3', this.assetRoot + 'assets/images/particles/fire3.png');
    this.game.load.image('smoke', this.assetRoot + 'assets/images/particles/smoke-puff.png');
  };

  Gameplay.prototype.create = function() {
    GameState.prototype.create.apply(this, arguments);

    this.game.physics.startSystem(Phaser.Physics.ARCADE);
    Display.init(this.game);
    Keyboard.init(this.game);

    this.bg = new Phaser.TileSprite(this, 0, 0, 0, 0, 'bg-texture');
    this.bg.height = this.game.height * 2;
    this.bg.width = this.game.width * 2;
    this.bg.scale = { x: 0.5, y: 0.5 };
    this.add.existing(this.bg);

    this.game.emitter = this.game.add.emitter(this.game.world.centerX, this.game.world.centerY, 400);

    this.game.emitter.makeParticles( [ 'fire1', 'fire2', 'fire3', 'smoke' ] );

    this.game.emitter.gravity = 200;
    this.game.emitter.setAlpha(1, 0, 3000);
    this.game.emitter.setScale(0.8, 0, 0.8, 0, 3000);

    this.game.emitter.start(false, 3000, 5);
    this.game.emitter.on = false;

    this.game.send("Ping", { timestamp: new Date().getTime() });
    this.game.time.events.loop(Phaser.Timer.SECOND * 10, function() {
      this.game.send("Ping", { timestamp: new Date().getTime() });
    }, this);

    if(cfg.initialAction === undefined) {
      cfg.initialAction = ["start"];
    }
    switch(cfg.initialAction[0]) {
      case "start":
        if(cfg.seed === undefined) {
          this.game.send("StartGame", {"rules": cfg.rules});
        } else {
          this.game.send("StartGame", {"rules": cfg.rules, "seed": cfg.seed});
        }
        break;
      case "join":
        this.game.send("JoinGame", {"id": cfg.initialAction[1]});
        break;
      case "observe":
        if(cfg.initialAction.length === 2) {
          this.game.send("ObserveGame", {"id": cfg.initialAction[1]});
        } else {
          this.game.send("ObserveGame", {"id": cfg.initialAction[1], "as": cfg.initialAction[2]});
        }
        break;
      default:
        alert("Invalid initial action [" + cfg.initialAction + "].");
    }
  };

  Gameplay.prototype.onMessage = function(c, v) {
    switch(c) {
      case "GameJoined":
        this.game.playmat = new Playmat(this.game, v.state.pileSets, v.state.layout);
        this.game.id = v.state.gameId;
        this.game.rules = v.rules;
        this.game.seed = v.state.seed;
        this.game.possibleMoves = v.moves;
        this.loadPileSets(v.state.pileSets);
        this.loadCards(v.state.pileSets);
        break;
      case "PossibleMoves":
        this.game.possibleMoves = v.moves;
        if(v.moves.length === 0) {
          alert("No more moves available.");
        }
        break;
      case "CardRevealed":
        var existing = this.game.cards[v.card.id];
        var wasFaceUp = existing.faceUp;
        existing.rank = Rank.fromChar(v.card.r);
        existing.suit = Suit.fromChar(v.card.s);
        if(v.card.u && !wasFaceUp) {
          existing.turnFaceUp();
        }
        break;
      case "CardHidden":
        var hidden = this.game.cards[v.id];
        hidden.turnFaceDown();
        break;
      case "CardMoved":
        this.moveCard(v.card, v.source, v.target, v.turn);
        break;
      case "CardsMoved":
        for(var cardIndex in v.cards) {
          this.moveCard(v.cards[cardIndex], v.source, v.target, v.turn);
        }
        break;
      case "CardMoveCancelled":
        for(var cardCancelledIndex in v.cards) {
          this.game.cards[v.cards[cardCancelledIndex]].cancelDrag();
        }
        break;
      case "GameLost":
        alert("You lose!");
        break;
      case "GameWon":
        alert("You win!");
        break;
      case "Reconnect":
        this.game.playmat.destroy();
        this.game.send("JoinGame", { "id": this.game.id });
        console.log("Reconnecting to game [" + this.game.id + "].");
        break;
      case "ServerError":
        console.error("Server error encountered.", v);
        break;
      default:
        GameState.prototype.onMessage.apply(this, arguments);
    }
  };

  Gameplay.prototype.moveCard = function(card, src, tgt, turn) {
    var movedCard = this.game.cards[card];
    var source = this.game.piles[src];
    var target = this.game.piles[tgt];

    if(turn === true && !movedCard.faceUp) {
      movedCard.turnFaceUp();
    }
    if(turn === false && movedCard.faceUp) {
      movedCard.turnFaceDown();
    }

    movedCard.bringToTop();
    source.removeCard(movedCard);
    target.addCard(movedCard);
  };

  Gameplay.prototype.loadPileSets = function(pileSets) {
    this.game.pileSets = pileSets;
    for(var pileSetIndex in pileSets) {
      var pileSet = pileSets[pileSetIndex];
      for(var pileIndex in pileSet.piles) {
        var pile = pileSet.piles[pileIndex];
        var pileObj = new Pile(this.game, pile.id, pileSet, pile.options);
        this.game.playmat.addPile(pileObj);
      }
    }
  };

  Gameplay.prototype.loadCards = function(pileSets) {
    for(var pileSetIndex in pileSets) {
      var pileSet = pileSets[pileSetIndex];
      for(var pileIndex in pileSet.piles) {
        var pile = pileSet.piles[pileIndex];
        var pileObj = this.game.piles[pile.id];
        for(var cardIndex in pile.cards) {
          var card = pile.cards[cardIndex];
          var cardObj = new Card(this.game, card.id, card.r, card.s, card.u);
          pileObj.addCard(cardObj);
        }
      }
    }
  };

  Gameplay.prototype.resize = function() {
    if(this.lastSize === undefined || this.lastSize[0] !== this.game.width || this.lastSize[1] !== this.game.height) {
      GameState.prototype.resize.apply(this, arguments);

      this.lastSize = [this.game.width, this.game.height];

      if(this.game.playmat !== undefined) {
        this.game.playmat.resize();
      }

      if(this.bg !== undefined) {
        this.bg.height = this.game.height * 2;
        this.bg.width = this.game.width * 2;
      }
    }
  };

  return Gameplay;
});
