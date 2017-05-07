package phaser.pile

import com.definitelyscala.phaser.{Group, Pointer, Sprite}
import models.SelectPile
import models.pile.Pile
import phaser.PhaserGame
import phaser.card.CardSprite

import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
class PileGroup(val phaser: PhaserGame, val pile: Pile) extends Group(game = phaser, parent = phaser.getPlaymat) {
  name = "pile-" + pile.id

  var cards = Seq.empty[CardSprite]
  var dragCards = Seq.empty[CardSprite]

  def id = pile.id

  def canSelectPile() = phaser.possibleMoves.exists { move =>
    move.moveType == "select-pile" && move.sourcePile == pile.id;
  }

  val empty = {
    val ret = new Sprite(game, 0, 0, "empty-piles", 0)
    ret.inputEnabled = true
    ret.events.onInputUp.add((e: Any, p: Pointer) => {
      if (p.button.toString.toInt == 0) {
        if (canSelectPile()) {
          val msg = SelectPile(pile.id, auto = false)
          phaser.sendMove(msg)
        }
      }
    }, this, 0.0)
    ret.anchor.x = 0.5
    ret.anchor.y = 0.5
    add(ret)
    ret
  }

  var intersectWidth = empty.width
  var intersectHeight = empty.height

  def addCard(sprite: CardSprite, cardPileIndex: Option[Int], animate: Boolean): Unit = {
    sprite.pileOption = Some(this)
    sprite.pileIndex = cardPileIndex.getOrElse(cards.length)
    cards = (cards :+ sprite).sortBy(_.pileIndex)

    PileCardHelper.cardAdded(this, sprite)

    if (animate) {
      phaser.getPlaymat.resizer.refreshLayout(animate)
    } else {
      phaser.getPlaymat.add(sprite)
    }
  }

  def removeCard(card: CardSprite) = {
    if (card.pileGroup != this) {
      throw new IllegalStateException("Provided card is not a part of this pile.")
    }

    card.pileOption = None
    card.pileIndex = -1

    cards = cards.filterNot(_ == card)
    cards.zipWithIndex.foreach { c =>
      c._1.pileIndex = c._2
    }

    PileCardHelper.cardRemoved(this, card)
  }
}
