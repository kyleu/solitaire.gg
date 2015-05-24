define(['utils/Config', 'game/state/Gameplay', 'game/state/GameState'], function (cfg, Gameplay, GameState) {
  "use strict";

  function LoadingScreen(game) {
    GameState.call(this, 'loading', game);

    this.assetRoot = "/";
    if(cfg.assetRoot !== undefined) {
      this.assetRoot = cfg.assetRoot;
    }
  }

  LoadingScreen.prototype = Object.create(GameState.prototype);
  LoadingScreen.prototype.constructor = LoadingScreen;

  LoadingScreen.prototype.preload = function() {
    this.message = this.game.add.text(this.game.world.centerX - 90, this.game.world.centerY - 60, "Loading...", { font: "18px Helvetica", fill: "#ffffff"});

    this.bg = new Phaser.TileSprite(this, 0, 0, 0, 0, 'bg-texture');
    this.bg.height = this.game.height * 2;
    this.bg.width = this.game.width * 2;
    this.bg.scale = { x: 0.5, y: 0.5 };
    this.add.existing(this.bg);

    this.loadBar = this.add.sprite(this.game.width * 0.1, this.game.world.centerY, 'load-bar');
    this.loadBar.width = this.game.width * 0.8;

    this.game.load.setPreloadSprite(this.loadBar);

    var imageKey = this.game.cardSet.key;
    this.game.load.spritesheet(
      'empty-piles', this.assetRoot + 'assets/images/game/cards/' + imageKey + '/empty.png', this.game.cardSet.cardWidth, this.game.cardSet.cardHeight
    );
    this.game.load.spritesheet(
      'card', this.assetRoot + 'assets/images/game/cards/' + imageKey + '/all.png', this.game.cardSet.cardWidth, this.game.cardSet.cardHeight
    );

    this.game.load.spritesheet('suits', this.assetRoot + 'assets/images/particles/suits.png', 100, 100);

    this.game.load.image('fire1', this.assetRoot + 'assets/images/particles/fire1.png');
    this.game.load.image('fire2', this.assetRoot + 'assets/images/particles/fire2.png');
    this.game.load.image('fire3', this.assetRoot + 'assets/images/particles/fire3.png');
    this.game.load.image('smoke', this.assetRoot + 'assets/images/particles/smoke-puff.png');

    var gameplay = new Gameplay(this.game);
    this.game.state.add('gameplay', gameplay);
  };

  LoadingScreen.prototype.create = function() {
    GameState.prototype.create.apply(this, arguments);

    this.game.state.start('gameplay');
  };

  return LoadingScreen;
});
