define([
  'utils/Config', 'game/Card', 'game/pile/Pile', 'game/pile/PileHelpers',
  'game/Playmat', 'game/state/GameState'
], function (cfg, Card, Pile, PileHelpers, Playmat, GameState) {
  "use strict";

  function Gameplay(game) {
    GameState.call(this, 'gameplay', game);
  }

  Gameplay.prototype = Object.create(GameState.prototype);
  Gameplay.prototype.constructor = Gameplay;

  Gameplay.prototype.preload = function() {
    var imageKey = this.game.cardSet.key;
    this.game.load.image('bg-texture', '/assets/images/game/bg.jpg');
    this.game.load.image('card-back', '/assets/images/game/cards/' + imageKey + '/BACK.png');
    this.game.load.image('empty-pile', '/assets/images/game/cards/' + imageKey + '/EMPTY.png');
    this.game.load.spritesheet('card', '/assets/images/game/cards/' + imageKey + '/ALL.png', this.game.cardSet.cardWidth, this.game.cardSet.cardHeight);
  };

  Gameplay.prototype.create = function() {
    GameState.prototype.create.apply(this, arguments);

    var victoriousCheatKey = this.game.input.keyboard.addKey(Phaser.Keyboard.V);
    victoriousCheatKey.onDown.add(this.victorious, this);

    var helpKey = this.game.input.keyboard.addKey(Phaser.Keyboard.H);
    helpKey.onDown.add(this.logGame, this);

    var debugKey = this.game.input.keyboard.addKey(Phaser.Keyboard.SPACEBAR);
    debugKey.onDown.add(this.toggleDebug, this);

    this.bg = new Phaser.TileSprite(this, 0, 0, 0, 0, 'bg-texture');
    this.bg.height = this.game.height * 2;
    this.bg.width = this.game.width * 2;
    this.bg.scale = { x: 0.5, y: 0.5 };
    this.add.existing(this.bg);

    this.game.ws.send("Ping", { timestamp: new Date().getTime() });
    this.game.time.events.loop(Phaser.Timer.SECOND * 10, function() {
      this.game.ws.send("Ping", { timestamp: new Date().getTime() });
    }, this);

    this.game.scale.onOrientationChange.add(this.onOrientationChange, this);

    if(cfg.initialAction === undefined) {
      cfg.initialAction = ["start"];
    }
    switch(cfg.initialAction[0]) {
      case "start":
        if(cfg.seed === undefined) {
          this.game.ws.send("StartGame", {"variant": cfg.variant});
        } else {
          this.game.ws.send("StartGame", {"variant": cfg.variant, "seed": cfg.seed});
        }
        break;
      case "join":
        this.game.ws.send("JoinGame", {"id": cfg.initialAction[1]});
        break;
      case "observe":
        if(cfg.initialAction.length === 2) {
          this.game.ws.send("ObserveGame", {"id": cfg.initialAction[1]});
        } else {
          this.game.ws.send("ObserveGame", {"id": cfg.initialAction[1], "as": cfg.initialAction[2]});
        }
        break;
      default:
        alert("Invalid initial action [" + cfg.initialAction + "].");
    }
  };

  Gameplay.prototype.victorious = function() {
    console.log("Victorious!");
    for(var cardIndex in this.game.cards) {
      this.game.cards[cardIndex].animation = {id: "mouse", speed: 200 + Math.floor(Math.random() * 200)};
    }
  };

  Gameplay.prototype.logGame = function() {
    console.log("Game [" + this.game.id + "] (" + this.game.variant + "):");
    console.log(this.game);
    console.log("Piles:");
    for(var cardIndex in this.game.piles) {
      console.log(this.game.piles[cardIndex]);
    }
    console.log("Possible Moves:");
    for(var moveIndex in this.game.possibleMoves) {
      console.log(this.game.possibleMoves[moveIndex]);
    }
  };

  Gameplay.prototype.toggleDebug = function() {
    var debugPanels = document.getElementsByClassName("pdebug");
    if(debugPanels.length == 1) {
      if(debugPanels[0].style.display === "none") {
        debugPanels[0].style.display = "block";
      } else {
        debugPanels[0].style.display = "none";
      }
    }
  };

  Gameplay.prototype.onMessage = function(c, v) {
    switch(c) {
      case "GameJoined":
        this.game.playmat = new Playmat(this.game, v.state.layouts);
        this.game.id = v.state.gameId;
        this.game.variant = v.variant;
        this.game.seed = v.state.seed;
        this.game.possibleMoves = v.moves;
        this.loadPiles(v.state.piles);
        this.loadCards(v.state.piles);
        break;
      case "PossibleMoves":
        this.game.possibleMoves = v.moves;
        if(v.moves.length === 0) {
          alert("No more moves available.");
        }
        break;
      case "CardRevealed":
        var existing = this.game.cards[v.card.id];
        existing.updateSprite(v.card.r, v.card.s, v.card.u);
        if(v.card.u) {
          existing.turnFaceUp();
        }
        break;
      case "CardRemoved":
        var removedCard = this.game.cards[v.card];
        removedCard.pile.removeCard(removedCard);
        PileHelpers.tweenRemove(removedCard);
        break;
      case "CardMoved":
        var movedCard = this.game.cards[v.card];
        var source = this.game.piles[v.source];
        var target = this.game.piles[v.target];

        if(v.turnFaceUp) {
          movedCard.turnFaceUp();
        } else if(v.turnFaceDown) {
          movedCard.turnFaceDown();
        }

        movedCard.bringToTop();
        source.removeCard(movedCard);
        target.addCard(movedCard);
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
      case "ServerError":
        console.error("Server error encountered.", v);
        break;
      default:
        GameState.prototype.onMessage.apply(this, arguments);
    }
  };

  Gameplay.prototype.loadPiles = function(piles) {
    for(var pileIndex in piles) {
      var pile = piles[pileIndex];
      var pileObj = new Pile(this.game, pile.id, pile.behavior, pile.options);
      this.game.playmat.addPile(pileObj);
    }
  };

  Gameplay.prototype.loadCards = function(piles) {
    for(var pileIndex in piles) {
      var pile = piles[pileIndex];
      var pileObj = this.game.piles[pile.id];
      for(var cardIndex in pile.cards) {
        var card = pile.cards[cardIndex];
        var cardObj = new Card(this.game, card.id, card.r, card.s, card.u);
        pileObj.addCard(cardObj);
      }
    }
  };

  Gameplay.prototype.onOrientationChange = function (scale, prevOrientation) {
    console.log("Orientation change: Was [" + prevOrientation + "], now [" + scale.screenOrientation + "]. Window: [" + window.innerWidth + "x" +  window.innerHeight + "].");
    document.getElementById("game-container").style.width = window.innerWidth;
    document.getElementById("game-container").style.height = window.innerHeight;
  };

  Gameplay.prototype.resize = function(w, h) {
    GameState.prototype.resize.apply(this, arguments);

    if(this.game.playmat !== undefined) {
      this.game.playmat.resize();
    }

    if(this.bg !== undefined) {
      this.bg.height = this.game.height * 2;
      this.bg.width = this.game.width * 2;
    }
  };

  return Gameplay;
});
