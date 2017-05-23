package phaser.pile

import models.pile.set.PileSet
import phaser.card.{CardSprite, CardTweens}

object PileCardHelper {
  def cardAdded(pileGroup: PileGroup, card: CardSprite) = {
    if (pileGroup.x < 0 || pileGroup.y < 0) {
      CardTweens.tweenRemove(card)
      card.phaser.getPlaymat.emitter.emitFor(card)
    } else {
      val emitFor = pileGroup.behavior == PileSet.Behavior.Foundation
      if (pileGroup.options.cardsShown.isEmpty) {
        val offsetCount = if (pileGroup.cards.isEmpty) { 0 } else { pileGroup.cards.length - 1 }
        if (pileGroup.options.direction.contains("d")) {
          val newY = pileGroup.y + (offsetCount * pileGroup.phaser.getSettings.cardSet.vOffset)
          CardTweens.tweenCardTo(card, pileGroup.x, newY, 0, emitFor)
          val offsetWidth = offsetCount * pileGroup.phaser.getSettings.cardSet.vOffset
          pileGroup.intersectHeight = pileGroup.phaser.getSettings.cardSet.h.toDouble + offsetWidth
        } else if (pileGroup.options.direction.contains("r")) {
          val newX = pileGroup.y + ((pileGroup.cards.length - 1) * pileGroup.phaser.getSettings.cardSet.hOffset)
          CardTweens.tweenCardTo(card, newX, pileGroup.y, 0, emitFor)
          val offsetWidth = offsetCount * pileGroup.phaser.getSettings.cardSet.hOffset
          pileGroup.intersectWidth = pileGroup.phaser.getSettings.cardSet.w.toDouble + offsetWidth
        } else {
          throw new IllegalStateException(s"Invalid direction [${pileGroup.options.direction.getOrElse("?")}].")
        }
      } else if (pileGroup.options.cardsShown.contains(1)) {
        CardTweens.tweenCardTo(card, pileGroup.x, pileGroup.y, 0, emitFor)
      } else {
        PileRedrawHelper.redraw(pileGroup)
      }
    }
  }

  def cardRemoved(pileGroup: PileGroup, card: CardSprite) = {
    if (pileGroup.x < 0 || pileGroup.y < 0) {
      CardTweens.tweenRestore(card)
    } else {
      if (pileGroup.options.cardsShown.isEmpty) {
        val offsetCount = if (pileGroup.cards.isEmpty) { 0 } else { pileGroup.cards.length - 1 }
        if (pileGroup.options.direction.contains("d")) {
          val offsetWidth = offsetCount * pileGroup.phaser.getSettings.cardSet.vOffset
          pileGroup.intersectHeight = pileGroup.phaser.getSettings.cardSet.h.toDouble + offsetWidth
        } else if (pileGroup.options.direction.contains("r")) {
          val offsetWidth = offsetCount * pileGroup.phaser.getSettings.cardSet.hOffset
          pileGroup.intersectWidth = pileGroup.phaser.getSettings.cardSet.w.toDouble + offsetWidth
        } else {
          throw new IllegalStateException(s"Invalid direction [${pileGroup.options.direction.getOrElse("?")}].")
        }
      } else if (!pileGroup.options.cardsShown.contains(1)) {
        PileRedrawHelper.redraw(pileGroup)
      }
    }
  }
}
