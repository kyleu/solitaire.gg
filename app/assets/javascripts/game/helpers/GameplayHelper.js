define(['utils/Config', 'game/Card', 'game/pile/Pile'], function(cfg, Card, Pile) {
  var GameplayHelper = function(game) {
    this.game = game;
    this.assetRoot = "/";
    if(cfg.assetRoot !== undefined) {
      this.assetRoot = cfg.assetRoot;
    }
  };

  GameplayHelper.prototype.load = function() {
    this.game.stage.disableVisibilityChange = true;
    var imageKey = this.game.cardSet.key;
    this.game.load.image('bg-texture', this.assetRoot + 'assets/images/game/bg.jpg');
    this.game.load.image('card-back', this.assetRoot + 'assets/images/game/cards/' + imageKey + '/BACK.png');
    this.game.load.image('empty-pile', this.assetRoot + 'assets/images/game/cards/' + imageKey + '/EMPTY.png');
    this.game.load.spritesheet(
      'card', this.assetRoot + 'assets/images/game/cards/' + imageKey + '/ALL.png', this.game.cardSet.cardWidth, this.game.cardSet.cardHeight
    );

    this.game.load.spritesheet('suits', this.assetRoot + 'assets/images/particles/suits.png', 100, 100);

    this.game.load.image('fire1', this.assetRoot + 'assets/images/particles/fire1.png');
    this.game.load.image('fire2', this.assetRoot + 'assets/images/particles/fire2.png');
    this.game.load.image('fire3', this.assetRoot + 'assets/images/particles/fire3.png');
    this.game.load.image('smoke', this.assetRoot + 'assets/images/particles/smoke-puff.png');
  };

  GameplayHelper.prototype.sendInitialMessage = function() {
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

  GameplayHelper.prototype.loadPileSets = function(pileSets) {
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
  GameplayHelper.prototype.loadCards = function(pileSets) {
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

  GameplayHelper.prototype.moveCard = function(card, src, tgt, turn) {
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
  return GameplayHelper;
});
