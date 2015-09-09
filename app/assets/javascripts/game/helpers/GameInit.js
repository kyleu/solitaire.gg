/* global define:false */
/* global Phaser:false */
/* global Solitaire:false */
define(['utils/Config', 'ui/Options', 'game/helpers/GameInput', 'game/helpers/GameNetwork', 'game/helpers/GameTimer', 'state/InitialState', 'help/Help'],
function (config, Options, GameInput, GameNetwork, GameTimer, InitialState, Help) {
  'use strict';

  return function (game, ws) {
    game.id = null;
    game.cardSet = {
      cardWidth: 400,
      cardHeight: 600,
      cardHorizontalOffset: 80,
      cardVerticalOffset: 120
    };
    var initialState = new InitialState(game);
    game.initialized = false;
    game.possibleMoves = [];
    game.movesMade = 0;
    game.timer = new GameTimer();
    game.complete = false;

    var configOptions = {
      width: '100%',
      height: '100%',
      renderer: Phaser.AUTO,
      parent: 'game-container',
      state: initialState,
      transparent: true,
      resolution: 2
    };

    Phaser.Game.call(game, configOptions);

    game.options = new Options(game);
    game.status = {};
    game.piles = {};
    game.cards = {};

    game.help = new Help(game);

    GameNetwork.setGame(game);

    game.inputService = new GameInput(game);

    if(config.offline) {
      var self = game;
      var callback = function(json) {
        var ret = JSON.parse(json);
        self.onMessage(ret.c, ret.v);
      };

      game.offlineService = new Solitaire();
      game.offlineService.register(callback);
    } else {
      game.ws = ws;
    }
  };
});
