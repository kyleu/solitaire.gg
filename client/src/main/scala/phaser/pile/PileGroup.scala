package phaser.pile

import com.definitelyscala.phaser.{Group, Pointer, Sprite}
import models.SelectPile
import models.game.PossibleMove
import models.pile.Pile
import models.pile.set.PileSet
import phaser.PhaserGame
import phaser.card.CardSprite

import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
class PileGroup(val phaser: PhaserGame, p: Pile) extends Group(game = phaser, parent = phaser.getPlaymat) {
  val id = p.id
  val behavior = p.pileSet.map(_.behavior).getOrElse(throw new IllegalStateException(s"Missing pileset for pile [$id]."))
  val options = p.options

  def canDragFrom(sprite: CardSprite) = p.canDragFrom(p.cards.drop(sprite.pileIndex), phaser.gameplay.services.state)

  name = "pile-" + id

  var cards = Seq.empty[CardSprite]
  var dragCards = Seq.empty[CardSprite]

  def canSelectPile = phaser.possibleMoves.exists { move =>
    move.t == PossibleMove.Type.SelectPile && move.sourcePile == id;
  }

  val empty = {
    val ret = new Sprite(game, 0, 0, phaser.getImages.emptyPile, 0)
    ret.alpha = 0.3
    ret.inputEnabled = true
    ret.events.onInputUp.add((_: Any, p: Pointer) => {
      if (Option(p.button).isEmpty || p.button.toString.toInt == 0) {
        if (canSelectPile) {
          val msg = SelectPile(id, auto = false)
          phaser.sendMove(msg)
        }
      }
    }, this, 0.0)
    ret.anchor.x = 0.5
    ret.anchor.y = 0.5
    add(ret)
    ret
  }

  empty.visible = behavior != PileSet.Behavior.Pyramid

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
