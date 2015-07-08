/* global define:false */
/* global Phaser:false */
define(['utils/Config', 'state/GameState', 'state/LoadingScreen'], function (cfg, GameState, LoadingScreen) {
  'use strict';

  function InitialState(game) {
    GameState.call(this, 'initial', game);

    this.assetRoot = '/';
    if(cfg.assetRoot !== undefined) {
      this.assetRoot = cfg.assetRoot;
    }
  }

  InitialState.prototype = Object.create(GameState.prototype);
  InitialState.prototype.constructor = InitialState;

  InitialState.prototype.preload = function() {
    //this.game.load.image('bg-texture', this.assetRoot + 'assets/images/game/bg.jpg');
    this.game.load.image('load-bar', this.assetRoot + 'assets/images/load/bar.png');
  };

  InitialState.prototype.create = function() {
    GameState.prototype.create.apply(this, arguments);

    var loadingScreen = new LoadingScreen(this.game);
    this.game.state.add('loading', loadingScreen);

    this.game.time.advancedTiming = true;

    this.game.scale.scaleMode = Phaser.ScaleManager.NO_SCALE;
    var g = this.game;
    var resize = function() {
      g.state.getCurrentState().resize();
    };

    window.addEventListener('resize', function() {
      g.scale.setGameSize(window.innerWidth, window.innerHeight);
      if(g.playmat !== undefined) {
        resize();
      }
    });

    if(typeof Phaser.Plugin.Debug === 'function') {
      this.game.add.plugin(Phaser.Plugin.Debug);
    }

    this.game.send('GetVersion', {});
    this.game.state.start('loading');
  };

  return InitialState;
});
