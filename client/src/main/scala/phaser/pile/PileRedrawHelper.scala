package phaser.pile

import phaser.card.CardTweens

object PileRedrawHelper {
  def redraw(pileGroup: PileGroup) = {
    val opts = pileGroup.options
    if (pileGroup.cards.length < opts.cardsShown.getOrElse(-1)) {
      pileGroup.cards.zipWithIndex.foreach { c =>
        val (smallCard, smallIndex) = c
        var smallX = 0.0
        var smallY = 0.0
        if (opts.direction.contains("d")) {
          smallX = pileGroup.x
          smallY = pileGroup.y + (pileGroup.phaser.getSettings.cardSet.vOffset * smallIndex)
        } else if (opts.direction.contains("r")) {
          smallX = pileGroup.x + (pileGroup.phaser.getSettings.cardSet.hOffset * smallIndex)
          smallY = pileGroup.y
        } else {
          throw new IllegalStateException(s"Invalid direction [${opts.direction.getOrElse("?")}].")
        }
        CardTweens.tweenCardTo(smallCard, smallX, smallY, 0);
      }
    } else {
      pileGroup.cards.zipWithIndex.foreach { c =>
        val (largeCard, largeIndex) = c

        var largeX = 0.0
        var largeY = 0.0
        var offset = 0

        if ((pileGroup.cards.length - largeIndex) < opts.cardsShown.getOrElse(0)) {
          if (opts.cardsShown.isEmpty) {
            offset = pileGroup.cards.length - largeIndex
          } else {
            offset = opts.cardsShown.getOrElse(0) - (pileGroup.cards.length - largeIndex)
          }
        }
        if (opts.direction.contains("d")) {
          largeX = pileGroup.x
          largeY = pileGroup.y + (pileGroup.phaser.getSettings.cardSet.vOffset * offset)
        } else if (opts.direction.contains("r")) {
          largeX = pileGroup.x + (pileGroup.phaser.getSettings.cardSet.hOffset * offset)
          largeY = pileGroup.y
        } else {
          throw new IllegalStateException(s"Invalid direction [${opts.direction.getOrElse("?")}].")
        }

        CardTweens.tweenCardTo(largeCard, largeX, largeY, 0)
      }
    }

    var additionalWidth = pileGroup.cards.length - 1
    if (pileGroup.cards.length > opts.cardsShown.getOrElse(0)) {
      additionalWidth = opts.cardsShown.getOrElse(0) - 1
    }
    val additional = additionalWidth * pileGroup.phaser.getSettings.cardSet.hOffset
    pileGroup.intersectWidth = pileGroup.phaser.getSettings.cardSet.w.toDouble + additional
  }
}
