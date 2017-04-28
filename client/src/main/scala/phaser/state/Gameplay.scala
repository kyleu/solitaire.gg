package phaser.state

import com.definitelyscala.phaser.{PhysicsObj, State}
import game.ActiveGame
import models.pile.set.PileSet
import phaser.PhaserGame
import phaser.card.{CardImages, CardSprite}
import phaser.pile.PileGroup
import phaser.playmat.Playmat
import settings.PlayerSettings

import scala.scalajs.js.timers.setTimeout
import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
class Gameplay(g: PhaserGame, settings: PlayerSettings, onLoadComplete: () => Unit) extends State {
  override def preload() = {
    game.physics.startSystem(PhysicsObj.ARCADE)
    g.setImages(new CardImages(g, settings))
  }

  override def create() = {
    onLoadComplete()
  }

  def start(ag: ActiveGame) = {
    val playmat = new Playmat(g, ag.gameState.pileSets, ag.gameRules.layout)
    g.setPlaymat(playmat)

    loadPileSets(ag.gameState.pileSets)

    val cards = loadCards(ag.gameState.pileSets, ag.gameState.deck.originalOrder)
    playmat.setCards(cards)

    utils.Logging.info(s"Started game [${ag.id}] with rules [${ag.rules}].")
  }

  private[this] def loadPileSets(pileSets: Seq[PileSet]) = {
    pileSets.foreach { pileSet =>
      pileSet.piles.foreach { pile =>
        var pileObj = new PileGroup(g, pile)
        g.getPlaymat.addPile(pileObj)
      }
    }
  }

  private[this] def loadCards(pileSets: Seq[PileSet], originalOrder: Seq[Int]) = {
    val cards = collection.mutable.HashMap.empty[Int, (CardSprite, PileGroup, Int)]

    pileSets.foreach { pileSet =>
      pileSet.piles.foreach { pile =>
        val pileObj = g.getPlaymat.getPile(pile.id)
        pile.cards.zipWithIndex.foreach { x =>
          val (card, cardIndex) = x
          var cardObj = new CardSprite(g, card, 0, 0)
          cards(card.id) = (cardObj, pileObj, cardIndex);
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
