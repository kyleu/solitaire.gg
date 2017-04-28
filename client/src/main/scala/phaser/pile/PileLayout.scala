package phaser.pile

import phaser.card.{CardSprite, CardTweens}

object PileLayout {
  def redraw(pileGroup: PileGroup) = {
    if (pileGroup.cards.length < pileGroup.pile.options.cardsShown.getOrElse(-1)) {
      pileGroup.cards.zipWithIndex.foreach { c =>
        val (smallCard, smallIndex) = c
        var smallX = 0.0
        var smallY = 0.0
        if (pileGroup.pile.options.direction.contains("d")) {
          smallX = pileGroup.x
          smallY = pileGroup.y + (pileGroup.phaser.getSettings.cardSet.cardVerticalOffset * smallIndex)
        } else if (pileGroup.pile.options.direction.contains("r")) {
          smallX = pileGroup.x + (pileGroup.phaser.getSettings.cardSet.cardHorizontalOffset * smallIndex)
          smallY = pileGroup.y
        } else {
          throw new IllegalStateException(s"Invalid direction [${pileGroup.pile.options.direction.getOrElse("?")}].")
        }
        CardTweens.tweenCardTo(smallCard, smallX, smallY, 0);
      }
    } else {
      pileGroup.cards.zipWithIndex.foreach { c =>
        val (largeCard, largeIndex) = c

        var largeX = 0.0
        var largeY = 0.0
        var offset = 0

        if ((pileGroup.cards.length - largeIndex) < pileGroup.pile.options.cardsShown.getOrElse(0)) {
          if (pileGroup.pile.options.cardsShown.isEmpty) {
            offset = pileGroup.cards.length - largeIndex
          } else {
            offset = pileGroup.pile.options.cardsShown.getOrElse(0) - (pileGroup.cards.length - largeIndex)
          }
        }
        if (pileGroup.pile.options.direction.contains("d")) {
          largeX = pileGroup.x
          largeY = pileGroup.y + (pileGroup.phaser.getSettings.cardSet.cardVerticalOffset * offset)
        } else if (pileGroup.pile.options.direction.contains("r")) {
          largeX = pileGroup.x + (pileGroup.phaser.getSettings.cardSet.cardHorizontalOffset * offset)
          largeY = pileGroup.y
        } else {
          throw new IllegalStateException(s"Invalid direction [${pileGroup.pile.options.direction.getOrElse("?")}].")
        }

        CardTweens.tweenCardTo(largeCard, largeX, largeY, 0)
      }
    }

    var additionalWidth = pileGroup.cards.length - 1
    if (pileGroup.cards.length > pileGroup.pile.options.cardsShown.getOrElse(0)) {
      additionalWidth = pileGroup.pile.options.cardsShown.getOrElse(0) - 1
    }
    pileGroup.intersectWidth = pileGroup.phaser.getSettings.cardSet.cardWidth.toDouble + (additionalWidth * pileGroup.phaser.getSettings.cardSet.cardHorizontalOffset)
  }

  def cardAdded(pileGroup: PileGroup, card: CardSprite) {
    if (pileGroup.x < 0 || pileGroup.y < 0) {
      CardTweens.tweenRemove(card)
      card.phaser.getPlaymat.emitter.emitFor(card)
    } else {
      var emitFor = pileGroup.pile.pileSet.exists(_.behavior == "foundation")
      if (pileGroup.pile.options.cardsShown.isEmpty) {
        var offsetCount = if (pileGroup.cards.isEmpty) { 0 } else { pileGroup.cards.length - 1 }
        if (pileGroup.pile.options.direction.contains("d")) {
          var newY = pileGroup.y + (offsetCount * pileGroup.phaser.getSettings.cardSet.cardVerticalOffset)
          CardTweens.tweenCardTo(card, pileGroup.x, newY, 0, emitFor)
          pileGroup.intersectHeight = pileGroup.phaser.getSettings.cardSet.cardHeight.toDouble + (offsetCount * pileGroup.phaser.getSettings.cardSet.cardVerticalOffset)
        } else if (pileGroup.pile.options.direction.contains("r")) {
          var newX = pileGroup.y + ((pileGroup.cards.length - 1) * pileGroup.phaser.getSettings.cardSet.cardHorizontalOffset)
          CardTweens.tweenCardTo(card, newX, pileGroup.y, 0, emitFor)
          pileGroup.intersectWidth = pileGroup.phaser.getSettings.cardSet.cardWidth.toDouble + (offsetCount * pileGroup.phaser.getSettings.cardSet.cardHorizontalOffset)
        } else {
          throw new IllegalStateException(s"Invalid direction [${pileGroup.pile.options.direction.getOrElse("?")}].")
        }
      } else if (pileGroup.pile.options.cardsShown.contains(1)) {
        CardTweens.tweenCardTo(card, pileGroup.x, pileGroup.y, 0, emitFor)
      } else {
        redraw(pileGroup)
      }
    }
  }

  def cardRemoved(pileGroup: PileGroup, card: CardSprite) {
    if (pileGroup.x < 0 || pileGroup.y < 0) {
      CardTweens.tweenRestore(card)
    } else {
      if (pileGroup.pile.options.cardsShown.isEmpty) {
        var offsetCount = if (pileGroup.cards.isEmpty) { 0 } else { pileGroup.cards.length - 1 }
        if (pileGroup.pile.options.direction.contains("d")) {
          pileGroup.intersectHeight = pileGroup.phaser.getSettings.cardSet.cardHeight.toDouble + (offsetCount * pileGroup.phaser.getSettings.cardSet.cardVerticalOffset)
        } else if (pileGroup.pile.options.direction.contains("r")) {
          pileGroup.intersectWidth = pileGroup.phaser.getSettings.cardSet.cardWidth.toDouble + (offsetCount * pileGroup.phaser.getSettings.cardSet.cardHorizontalOffset)
        } else {
          throw new IllegalStateException(s"Invalid direction [${pileGroup.pile.options.direction.getOrElse("?")}].")
        }
      } else if (!pileGroup.pile.options.cardsShown.contains(1)) {
        redraw(pileGroup)
      }
    }
  }
}
