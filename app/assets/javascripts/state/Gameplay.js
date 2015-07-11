/* global define:false */
/* global Phaser:false */
/* global _:false */
define([
  'utils/Config', 'card/Rank', 'card/Suit', 'card/Card', 'card/Tweens', 'pile/Pile',
  'game/helpers/Backdrop', 'game/helpers/Display', 'game/helpers/Keyboard',
  'game/Playmat', 'state/GameState', 'state/GameplayLoadHelper', 'state/GameplayMessageHelper'
], function (cfg, Rank, Suit, Card, Tweens, Pile, Backdrop, Display, Keyboard, Playmat, GameState, GameplayLoadHelper, GameplayMessageHelper) {
  'use strict';

  function Gameplay(game) {
    GameState.call(this, 'gameplay', game);
    this.loadHelper = new GameplayLoadHelper(game);
    this.messageHelper = new GameplayMessageHelper(game);
  }

  Gameplay.prototype = Object.create(GameState.prototype);
  Gameplay.prototype.constructor = Gameplay;

  Gameplay.prototype.preload = function() {
    this.loadHelper.load();
  };

  Gameplay.prototype.create = function() {
    GameState.prototype.create.apply(this, arguments);

    this.game.physics.startSystem(Phaser.Physics.ARCADE);
    Display.init(this.game);
    this.game.keyboard = new Keyboard(this.game);
    this.game.keyboard.init();

    this.backdrop = new Backdrop(this.game);
    this.add.existing(this.backdrop.bg);

    this.game.send('Ping', { timestamp: new Date().getTime() });
    this.game.time.events.loop(Phaser.Timer.SECOND * 10, function() {
      this.game.send('Ping', { timestamp: new Date().getTime() });
    }, this);

    this.messageHelper.sendInitialMessage();
  };

  Gameplay.prototype.onMessage = function(c, v) {
    this.messageHelper.onMessage(c, v);
  };

  Gameplay.prototype.resize = function() {
    if(this.lastSize === undefined || this.lastSize[0] !== this.game.width || this.lastSize[1] !== this.game.height) {
      GameState.prototype.resize.apply(this, arguments);

      this.lastSize = [this.game.width, this.game.height];

      if(this.game.playmat !== undefined) {
        this.game.playmat.resize();
      }

      if(this.backdrop !== undefined) {
        this.backdrop.height = this.game.height;
        this.backdrop.width = this.game.width;
      }
    }
  };

  return Gameplay;
});
