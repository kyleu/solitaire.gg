package phaser.gameplay

import models.pile.set.PileSet
import phaser.PhaserGame
import phaser.card.CardSprite
import phaser.pile.PileGroup

import scala.scalajs.js.timers.setTimeout

object AssetLoader {
  def loadPileSets(g: PhaserGame, pileSets: Seq[PileSet]) = {
    pileSets.foreach { pileSet =>
      pileSet.piles.foreach { pile =>
        var pileObj = new PileGroup(g, pile)
        g.getPlaymat.addPile(pileObj)
      }
    }
  }

  def loadCards(g: PhaserGame, pileSets: Seq[PileSet], originalOrder: Seq[Int]) = {
    val cards = collection.mutable.HashMap.empty[Int, (CardSprite, PileGroup, Int)]

    pileSets.foreach { pileSet =>
      pileSet.piles.foreach { pile =>
        val pileObj = g.getPlaymat.getPileGroup(pile.id)
        pile.cards.zipWithIndex.foreach { x =>
          val (card, cardIndex) = x
          val initialX = (g.width / 2) / g.getPlaymat.scale.x
          val initialY = g.height / g.getPlaymat.scale.y
          val cardObj = new CardSprite(g, card.id, card.r, card.s, card.u, initialX.toInt, initialY.toInt)
          cards(card.id) = (cardObj, pileObj, cardIndex)
        }
      }
    }

    originalOrder.zipWithIndex.map { x =>
      val (cardId, cardIndex) = x
      val c = cards.getOrElse(cardId, throw new IllegalStateException(s"Unknown card [$cardId]."))
      val cardObj = c._1
      val pileObj = c._2
      val cardPileIndex = c._3
      setTimeout(30.0 * cardIndex)(pileObj.addCard(cardObj, Some(cardPileIndex)))
    }

    setTimeout((30.0 * (originalOrder.length - 1)) + 500)(g.getPlaymat.initialMovesComplete())

    cards.toMap
  }
}
