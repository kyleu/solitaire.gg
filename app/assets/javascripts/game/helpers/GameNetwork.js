/* global define:false */
/* global _:false */
define(['utils/Config', 'ui/Prompts'], function (config, Prompts) {
  'use strict';

  var game;
  var recentMoves = [];

  var maxAcceptableLatency = 500;

  return {
    setGame: function(g) { game = g; },

    onMessage: function(c, v) {
      //if(c !== 'Pong') {
      //  console.log('Message [' + c + '] received with content [' + JSON.stringify(v) + '].');
      //}
      switch(c) {
        case 'Pong':
          var latency = (new Date().getTime() - v.timestamp);
          game.status.latency = latency;
          if(latency > maxAcceptableLatency) {
            console.log('Latency: ' + latency + 'ms');
            Prompts.offerOffline();
          }
          break;
        case 'VersionResponse':
          game.status.version = v.version;
          break;
        case 'Notification':
          alert(v.message);
          break;
        default:
          game.state.getCurrentState().onMessage(c, v);
      }
    },

    autoMove: function() {
      var idx = 0;
      var move = null;
      var candidate = null;
      var matches = function(e) { return _.isEqual(e, candidate); };
      while(move === null && game.possibleMoves.length > idx) {
        candidate = game.possibleMoves[idx];
        if(_.find(recentMoves, matches) !== undefined) {
          idx += 1;
        } else {
          move = candidate;
        }
      }
      if(move !== null) {
        if(move.moveType === 'move-cards') {
          recentMoves.push(move);
          if(recentMoves.length > 50) {
            recentMoves = recentMoves.splice(recentMoves.length - 50, 0);
          }
        }
        game.sendMove(move);
      }
    },

    sendMove: function(move) {
      game.movesMade += 1;
      if(game.timer.started === undefined || game.timer.started === null) {
        game.timer.start();
      }
      switch(move.moveType) {
        case 'move-cards':
          game.send('MoveCards', { cards: move.cards, src: move.sourcePile, tgt: move.targetPile });
          break;
        case 'select-card':
          var selectedCard = game.cards[move.cards[0]];
          game.send('SelectCard', { card: selectedCard.id, pile: selectedCard.pile.id });
          break;
        case 'select-pile':
          var selectedPile = game.piles[move.sourcePile];
          game.send('SelectPile', { pile: selectedPile.id } );
          break;
        default:
          throw 'Unknown move [' + move.moveType + '].';
      }
    },

    send: function(c, v) {
      if(config.offline) {
        game.offlineService.receive(c, v);
      } else {
        game.ws.send(c, v);
      }
    }
  };
});
