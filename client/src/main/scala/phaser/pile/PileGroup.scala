package phaser.pile

import com.definitelyscala.phaser.{Group, Pointer, Sprite}
import models.pile.Pile
import models.{MoveCards, SelectPile}
import phaser.PhaserGame
import phaser.card.{CardSprite, CardTweens}

import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
class PileGroup(val phaser: PhaserGame, val pile: Pile) extends Group(game = phaser, parent = phaser.getPlaymat) {
  this.name = "pile-" + pile.id

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

  def addCard(card: CardSprite, cardPileIndex: Option[Int]) = {
    card.pileOption = Some(this)
    card.pileIndex = cardPileIndex.getOrElse(cards.length)
    cards = (cards :+ card).sortBy(_.pileIndex)

    PileLayout.cardAdded(this, card)

    if (phaser.initialized) {
      phaser.getPlaymat.resizer.refreshLayout()
    } else {
      phaser.getPlaymat.add(card)
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

    PileLayout.cardRemoved(this, card)
  }

  def canDragFrom(sprite: CardSprite) = {
    val stripe = pile.cards.drop(sprite.pileIndex)
    pile.canDragFrom(stripe, phaser.gameplay.getState)
  }

  def startDrag(card: CardSprite, p: Pointer) = {
    dragCards = cards.drop(card.pileIndex)
    dragCards.zipWithIndex.foreach { x =>
      val (c, idx) = x
      CardTweens.tweenPickUp(c)
      c.startDrag(p, idx)
    }
  }

  def endDrag() = {
    val dropTarget = PileHelpers.getDropTarget(this)

    println(dropTarget)

    dropTarget match {
      case None => dragCards.foreach { cancelCard =>
        cancelCard.dragIndex = None
        cancelCard.cancelDrag()
      }
      case Some(dt) =>
        // console.log('Moving [' + this.dragCards + '] to [' + dropTarget.id + '].');
        dragCards.foreach { moveCard =>
          moveCard.dragIndex = None
        }
        val cardIds = dragCards.map(_.id)
        val dropId = dropTarget.map(_.pile.id).getOrElse("?")
        val msg = MoveCards(cardIds, pile.id, dropId, auto = false)
        phaser.sendMove(msg)
    }
    dragCards = Seq.empty
  }
}
