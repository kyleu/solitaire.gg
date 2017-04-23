package phaser.pile

import phaser.card.{CardSprite, CardTweens}

object PileLayout {
  def redraw(pile: PileGroup) = {
    if (pile.cards.length < pile.options.cardsShown.getOrElse(-1)) {
      pile.cards.zipWithIndex.foreach { c =>
        val (smallCard, smallIndex) = c
        var smallX = 0.0
        var smallY = 0.0
        if (pile.options.direction.contains("d")) {
          smallX = pile.x
          smallY = pile.y + (pile.phaser.getSettings.cardSet.cardVerticalOffset * smallIndex)
        } else if (pile.options.direction.contains("r")) {
          smallX = pile.x + (pile.phaser.getSettings.cardSet.cardHorizontalOffset * smallIndex)
          smallY = pile.y
        } else {
          throw new IllegalStateException(s"Invalid direction [${pile.options.direction.getOrElse("?")}].")
        }
        CardTweens.tweenCardTo(smallCard, smallX, smallY, 0);
      }
    } else {
      pile.cards.zipWithIndex.foreach { c =>
        val (largeCard, largeIndex) = c

        var largeX = 0.0
        var largeY = 0.0
        var offset = 0

        if ((pile.cards.length - largeIndex) < pile.options.cardsShown.getOrElse(0)) {
          if (pile.options.cardsShown.isEmpty) {
            offset = pile.cards.length - largeIndex
          } else {
            offset = pile.options.cardsShown.getOrElse(0) - (pile.cards.length - largeIndex)
          }
        }
        if (pile.options.direction.contains("d")) {
          largeX = pile.x
          largeY = pile.y + (pile.phaser.getSettings.cardSet.cardVerticalOffset * offset)
        } else if (pile.options.direction.contains("r")) {
          largeX = pile.x + (pile.phaser.getSettings.cardSet.cardHorizontalOffset * offset)
          largeY = pile.y
        } else {
          throw new IllegalStateException(s"Invalid direction [${pile.options.direction.getOrElse("?")}].")
        }

        CardTweens.tweenCardTo(largeCard, largeX, largeY, 0)
      }
    }

    var additionalWidth = pile.cards.length - 1
    if (pile.cards.length > pile.options.cardsShown.getOrElse(0)) {
      additionalWidth = pile.options.cardsShown.getOrElse(0) - 1
    }
    pile.intersectWidth = pile.phaser.getSettings.cardSet.cardWidth.toDouble + (additionalWidth * pile.phaser.getSettings.cardSet.cardHorizontalOffset)
  }

  def cardAdded(pile: PileGroup, card: CardSprite) {
    if (pile.x < 0 || pile.y < 0) {
      CardTweens.tweenRemove(card)
      card.phaser.getPlaymat.emitter.emitFor(card)
    } else {
      var emitFor = pile.pileSet.behavior == "foundation"
      if (pile.options.cardsShown.isEmpty) {
        var offsetCount = if (pile.cards.length == 0) { 0 } else { pile.cards.length - 1 }
        if (pile.options.direction.contains("d")) {
          var newY = pile.y + (offsetCount * pile.phaser.getSettings.cardSet.cardVerticalOffset)
          CardTweens.tweenCardTo(card, pile.x, newY, 0, emitFor)
          pile.intersectHeight = pile.phaser.getSettings.cardSet.cardHeight.toDouble + (offsetCount * pile.phaser.getSettings.cardSet.cardVerticalOffset)
        } else if (pile.options.direction.contains("r")) {
          var newX = pile.y + ((pile.cards.length - 1) * pile.phaser.getSettings.cardSet.cardHorizontalOffset)
          CardTweens.tweenCardTo(card, newX, pile.y, 0, emitFor)
          pile.intersectWidth = pile.phaser.getSettings.cardSet.cardWidth.toDouble + (offsetCount * pile.phaser.getSettings.cardSet.cardHorizontalOffset)
        } else {
          throw new IllegalStateException(s"Invalid direction [${pile.options.direction.getOrElse("?")}].")
        }
      } else if (pile.options.cardsShown.contains(1)) {
        CardTweens.tweenCardTo(card, pile.x, pile.y, 0, emitFor)
      } else {
        redraw(pile)
      }
    }
  }

  def cardRemoved(pile: PileGroup, card: CardSprite) {
    if (pile.x < 0 || pile.y < 0) {
      CardTweens.tweenRestore(card)
    } else {
      if (pile.options.cardsShown.isEmpty) {
        var offsetCount = if (pile.cards.length == 0) { 0 } else { pile.cards.length - 1 }
        if (pile.options.direction.contains("d")) {
          pile.intersectHeight = pile.phaser.getSettings.cardSet.cardHeight.toDouble + (offsetCount * pile.phaser.getSettings.cardSet.cardVerticalOffset)
        } else if (pile.options.direction.contains("r")) {
          pile.intersectWidth = pile.phaser.getSettings.cardSet.cardWidth.toDouble + (offsetCount * pile.phaser.getSettings.cardSet.cardHorizontalOffset)
        } else {
          throw new IllegalStateException(s"Invalid direction [${pile.options.direction.getOrElse("?")}].")
        }
      } else if (!pile.options.cardsShown.contains(1)) {
        redraw(pile)
      }
    }
  }
}
