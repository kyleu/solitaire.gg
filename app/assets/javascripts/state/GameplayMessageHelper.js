/* global define:false */
/* global _:false */
define(['utils/Config', 'card/Rank', 'card/Suit', 'state/GameState'], function (cfg, Rank, Suit, GameState) {
  var GameplayMessageHelper = function(game, loadHelper) {
    this.game = game;
    this.loadHelper = loadHelper;
  };

  GameplayMessageHelper.prototype.sendInitialMessage = function() {
    if(cfg.initialAction === undefined) {
      cfg.initialAction = ['start'];
    }

    switch(cfg.initialAction[0]) {
      case 'start':
        if(cfg.seed === undefined) {
          this.game.send('StartGame', { rules: cfg.rules });
        } else {
          this.game.send('StartGame', { rules: cfg.rules, seed: cfg.seed });
        }
        break;
      case 'join':
        this.game.send('JoinGame', { id: cfg.initialAction[1] });
        break;
      case 'observe':
        if(cfg.initialAction.length === 2) {
          this.game.send('ObserveGame', { id: cfg.initialAction[1] });
        } else {
          this.game.send('ObserveGame', { id: cfg.initialAction[1], as: cfg.initialAction[2] });
        }
        break;
      default:
        alert('Invalid initial action [' + cfg.initialAction + '].');
    }
  };

  GameplayMessageHelper.prototype.moveCard = function(card, src, tgt, turn) {
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

  GameplayMessageHelper.prototype.onMessage = function(c, v) {
    var self = this;
    switch(c) {
      case 'GameJoined':
        if(this.game.cardTextures === undefined) {
          this.loadHelper.loadCardImages();
        }
        this.game.join(v.state, v.elapsedMs, v.moves);
        this.loadHelper.loadPileSets(v.state.pileSets);
        this.loadHelper.loadCards(v.state.pileSets, v.state.deck.originalOrder);
        break;
      case 'PossibleMoves':
        this.game.possibleMoves = v.moves;
        if(v.moves.length === 0) {
          alert('No more moves available.');
        }
        this.game.options.setUndosAvailable(v.undosAvailable);
        this.game.options.setRedosAvailable(v.redosAvailable);
        break;
      case 'CardRevealed':
        var existing = this.game.cards[v.card.id];
        var wasFaceUp = existing.faceUp;
        existing.rank = Rank.fromChar(v.card.r);
        existing.suit = Suit.fromChar(v.card.s);
        if(v.card.u && !wasFaceUp) {
          existing.turnFaceUp();
        } else {
          console.warn('Reveal received for already revealed card [' + v.card.id + '].');
        }
        break;
      case 'CardHidden':
        var hidden = this.game.cards[v.id];
        hidden.turnFaceDown();
        break;
      case 'CardMoved':
        this.moveCard(v.card, v.source, v.target, v.turn);
        break;
      case 'CardsMoved':
        _.each(v.cards, function(movedCard) {
          self.moveCard(movedCard, v.source, v.target, v.turn);
        });
        break;
      case 'CardMoveCancelled':
        _.each(v.cards, function(cancelledCard) {
          self.game.cards[cancelledCard].cancelDrag();
        });
        break;
      case 'GameLost':
        self.game.playmat.lose();
        break;
      case 'GameWon':
        self.game.playmat.win();
        break;
      case 'Reconnect':
        this.game.playmat.destroy();
        this.game.send('JoinGame', { 'id': this.game.id });
        console.log('Reconnecting to game [' + this.game.id + '].');
        break;
      case 'ServerError':
        console.error('Server error encountered.', v);
        break;
      default:
        GameState.prototype.onMessage.apply(this, arguments);
    }
  };

  return GameplayMessageHelper;
});
