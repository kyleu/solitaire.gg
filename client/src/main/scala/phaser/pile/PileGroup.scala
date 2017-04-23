package phaser.pile

import com.definitelyscala.phaser.{Group, Pointer, Sprite}
import models.MoveCards
import models.pile.options.PileOptions
import models.pile.set.PileSet
import phaser.PhaserGame
import phaser.card.{CardSprite, CardTweens}
import utils.NullUtils

import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
class PileGroup(val phaser: PhaserGame, val id: String, val pileSet: PileSet, val pileSetIndex: Int, val options: PileOptions) extends Group {
  var cards: Array[CardSprite] = Array.empty
  var dragCards: Array[CardSprite] = Array.empty

  val empty = {
    val ret = new Sprite(game, 0, 0, "empty-piles", 0)
    ret.inputEnabled = true
    ret.events.onInputUp.add((e: Any, p: Pointer) => {
      if (p.button.toString.toInt == 0) {
        //if(canSelectPile(this)) {
        //game.sendMove({'moveType': 'select-pile', sourcePile: this.id, auto: false});
        //}
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
    card.pile = this
    card.pileIndex = cardPileIndex.getOrElse(cards.length)
    cards(card.pileIndex) = card

    PileLayout.cardAdded(this, card)

    if (phaser.initialized) {
      phaser.getPlaymat.resizer.refreshLayout()
    } else {
      phaser.getPlaymat.add(card)
    }
  }

  def removeCard(card: CardSprite) = {
    if (card.pile != this) {
      throw new IllegalStateException("Provided card is not a part of this pile.")
    }

    card.pile = NullUtils.inst
    card.pileIndex = 0

    var index = cards.indexOf(card)
    cards = cards.filterNot(_ == card)
    cards.zipWithIndex.foreach { c =>
      c._1.pileIndex = c._2
    }

    PileLayout.cardRemoved(this, card)
  }

  def dragSlice(card: CardSprite, p: Pointer) = {
    dragCards = cards.drop(card.pileIndex)
    dragCards.zipWithIndex.foreach { x =>
      val (c, idx) = x
      CardTweens.tweenPickUp(c)
      c.startDrag(p, idx)
    }
  }

  def endDrag() = {
    var dropTarget = PileHelpers.getDropTarget(this)

    if (dropTarget.isEmpty) {
      this.dragCards.foreach { cancelCard =>
        cancelCard.dragging = false
        cancelCard.cancelDrag()
      }
    } else {
      // console.log('Moving [' + this.dragCards + '] to [' + dropTarget.id + '].');
      dragCards.foreach { moveCard =>
        moveCard.dragging = false
      }
      var cardIds = dragCards.map(_.id)
      val dropId = dropTarget.map(_.id).getOrElse("?")
      MoveCards(cardIds.toSeq, id, dropId, auto = false)
      // TODO this.game.sendMove({ moveType: 'move-cards', cards: cardIds, sourcePile: this.id, targetPile: dropTarget.id, auto: false });
    }
    dragCards = Array.empty
  }
}
