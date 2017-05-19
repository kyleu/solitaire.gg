package phaser.gameplay

import models.pile.set.PileSet
import phaser.PhaserGame
import phaser.card.CardSprite
import phaser.pile.PileGroup

import scala.scalajs.js.timers.setTimeout

object AssetLoader {
  def loadPileSets(g: PhaserGame, pileSets: Seq[PileSet]) = pileSets.foreach { pileSet =>
    pileSet.piles.foreach(pile => g.getPlaymat.addPile(new PileGroup(g, pile)))
  }

  private[this] def error(g: PhaserGame, cardId: Int, cards: Iterable[(CardSprite, PileGroup, Int)]) = {
    val cardIds = cards.map(_._1.id).toSeq.sorted.mkString(", ")
    val deckSize = g.gameplay.services.state.deck.cards.size
    val msg = s"Unknown card [$cardId]. [$deckSize] cards in deck. [${cards.size}] cards available: [$cardIds]."
    throw new IllegalStateException(msg)
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
      val c = cards.getOrElse(cardId, error(g, cardId, cards.values))
      val cardObj = c._1
      val pileObj = c._2
      val cardPileIndex = c._3
      setTimeout(30.0 * cardIndex)(pileObj.addCard(cardObj, Some(cardPileIndex), animate = false))
    }

    setTimeout((30.0 * (originalOrder.length - 1)) + 500)(g.getPlaymat.initialMovesComplete())

    cards.toMap
  }
}
