define(["game/Rank", "game/Suit"], function(Rank, Suit) {
  "use strict";

  var cardTextures = [];

  var blank, suitImages, smallSuitImages, rankImages;

  function init(game) {
    if(cardTextures.length > 0) { throw "Double initialize."; }

    blank = new Phaser.Image(game, 0, 0, 'card-blank', 0);

    suitImages = _.map([0, 1, 2, 3], function(i) {
      var ret = new Phaser.Image(game, 0, 0, 'card-suits', i);
      ret.anchor.setTo(0.5, 0.5);
      return ret;
    });
    smallSuitImages = _.map([0, 1, 2, 3], function(i) {
      var ret = new Phaser.Image(game, 0, 0, 'card-suits', i);
      ret.width = 60;
      ret.height = 60;
      ret.anchor.setTo(0.5, 0.5);
      return ret;
    });
    rankImages = _.map([0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12], function(i) {
      var ret = new Phaser.Image(game, 0, 0, 'card-ranks', i);
      ret.anchor.setTo(0.5, 0.5);
      return ret;
    });

    _.each(Suit.all, function(s) {
      _.each(Rank.all, function(r) {
        cardTextures[r.char + s.char] = renderCard(game, s, r, 'standard');
      });
    });

    //var card = game.add.sprite(10, 10, cardTextures.AH);
    //card.scale = new Phaser.Point(0.5, 0.5);
    //game.add.existing(card);
  }

  function renderCard(game, s, r, layout) {
    var tex = game.add.bitmapData(400, 600);

    tex.draw(blank, 0, 0);

    var suitImage = suitImages[s.index];
    var rankImage = rankImages[r.value - 2];

    switch(layout) {
      case 'standard':
        tex.draw(rankImage, 40, 40, 60, 60);
        tex.draw(suitImage, 120, 40, 60, 60);
        _.each(r.locs, function(loc) {
          tex.draw(suitImage, (loc[0] * 400), (loc[1] * 400) + 100, 60, 60);
        });
        break;
      case 'mobile':
        tex.draw(rankImage, 40, 40, 60, 60);
        tex.draw(suitImage, 120, 40, 60, 60);
        _.each(r.locs, function(loc) {
          tex.draw(suitImage, (loc[0] * 400), (loc[1] * 400) + 200, 60, 60);
        });
        break;
      default:
        throw "?";
    }

    return tex;
  }

  return {
    init: init,
    textures: cardTextures
  };
});
