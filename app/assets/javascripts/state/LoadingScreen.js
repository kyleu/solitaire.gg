/* global define:false */
define(['utils/Config', 'state/Gameplay', 'state/GameState', 'ui/Theme'], function (cfg, Gameplay, GameState, Theme) {
  'use strict';

  function LoadingScreen(game) {
    GameState.call(this, 'loading', game);

    this.assetRoot = '/';
    if(cfg.assetRoot !== undefined) {
      this.assetRoot = cfg.assetRoot;
    }
  }

  LoadingScreen.prototype = Object.create(GameState.prototype);
  LoadingScreen.prototype.constructor = LoadingScreen;

  LoadingScreen.prototype.preload = function() {
    this.message = this.game.add.text(this.game.world.centerX - 25, this.game.world.centerY - 60, 'Loading...', { font: '18px Helvetica', fill: '#ffffff'});

    this.loadBar = this.add.sprite(this.game.width * 0.1, this.game.world.centerY, 'load-bar');
    this.loadBar.width = this.game.width * 0.8;

    this.game.load.setPreloadSprite(this.loadBar);

    this.game.load.spritesheet(
      'empty-piles', this.assetRoot + 'assets/images/cards/empty-a.png', this.game.cardSet.cardWidth, this.game.cardSet.cardHeight
    );

    var preferences = Theme.init();

    this.game.load.image('card-blank', this.assetRoot + 'assets/images/cards/blank.png');
    this.game.load.image('card-back', this.assetRoot + 'assets/images/cards/back-' + preferences['card-back'] + '.png');
    this.game.load.spritesheet('card-suits', this.assetRoot + 'assets/images/cards/suits-' + preferences['card-suit'] + '.png', 200, 200);
    this.game.load.spritesheet('card-ranks', this.assetRoot + 'assets/images/cards/ranks-' + preferences['card-rank'] + '.png', 200, 200);
    this.game.load.spritesheet('card-faces', this.assetRoot + 'assets/images/cards/face-cards-' + preferences['card-face'] + '.png', 200, 300);

    var gameplay = new Gameplay(this.game);
    this.game.state.add('gameplay', gameplay);
  };

  LoadingScreen.prototype.create = function() {
    GameState.prototype.create.apply(this, arguments);

    this.game.state.start('gameplay');
  };

  return LoadingScreen;
});
