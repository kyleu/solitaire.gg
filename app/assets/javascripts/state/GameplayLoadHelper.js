/* global define:false */
/* global _:false */
define(['utils/Config', 'card/Card', 'card/CardImages', 'pile/Pile', 'ui/Theme'], function(cfg, Card, CardImages, Pile, Theme) {
  var GameplayLoadHelper = function(game) {
    this.game = game;
  };

  GameplayLoadHelper.prototype.load = function() {
    this.game.stage.disableVisibilityChange = true;
  };

  GameplayLoadHelper.prototype.loadCardImages = function() {
    CardImages.init(this.game, Theme.getPreferences());
    this.game.cardTextures = CardImages.textures;
  };

  GameplayLoadHelper.prototype.loadPileSets = function(pileSets) {
    this.game.pileSets = pileSets;
    var g = this.game;
    _.each(pileSets, function(pileSet) {
      _.each(pileSet.piles, function(pile) {
        var pileObj = new Pile(g, pile.id, pileSet, pile.options);
        g.playmat.addPile(pileObj);
      });
    });
  };

  GameplayLoadHelper.prototype.loadCards = function(pileSets, originalOrder) {
    var cards = {};
    var g = this.game;
    _.each(pileSets, function(pileSet) {
      _.each(pileSet.piles, function(pile) {
        var pileObj = g.piles[pile.id];
        _.each(pile.cards, function(card, cardIndex) {
          var cardObj = new Card(g, card.id, card.r, card.s, card.u);
          cards[card.id] = [cardObj, pileObj, cardIndex];
        });
      });
    });

    _.each(originalOrder, function(cardId, cardIndex) {
      var c = cards[cardId];
      if(c !== undefined) {
        var cardObj = c[0];
        var pileObj = c[1];
        var cardPileIndex = c[2];
        setTimeout(function() { pileObj.addCard(cardObj, cardPileIndex); }, 30 * cardIndex);
      }
    });

    var game = this.game;
    setTimeout(function() { game.initialMovesComplete(); }, (30 * (originalOrder.length - 1)) + 500);
  };

  return GameplayLoadHelper;
});
