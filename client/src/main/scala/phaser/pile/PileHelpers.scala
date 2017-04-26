package phaser.pile

import com.definitelyscala.phaser.Pointer
import models.MoveCards
import phaser.card.{CardSprite, CardTweens}

object PileHelpers {
  private[this] def isValidMove(src: PileGroup, tgt: PileGroup) = {
    var valid = false

    def check(c: Int, idx: Int) = if (c != src.dragCards(idx).id) {
      valid = false
    }

    src.phaser.possibleMoves.moves.foreach { move =>
      if (move.moveType == "move-cards" && move.sourcePile == src.id && move.targetPile.contains(tgt.id)) {
        if (src.dragCards.length == move.cards.length) {
          valid = true
          move.cards.zipWithIndex.foreach(c => check(c._1, c._2))
        }
      }
    }

    valid
  }

  def getDropTarget(pile: PileGroup) = {
    val firstCard = pile.dragCards.head

    val multiplier = 0.9; // Adjust for growth while being dragged.

    val minX = firstCard.x
    val maxX = firstCard.x + (firstCard.width * multiplier)
    val xPoint = firstCard.x + (firstCard.anchorPointX * multiplier)
    val minY = firstCard.y
    val maxY = firstCard.y + (firstCard.height * multiplier)
    val yPoint = firstCard.y + (firstCard.anchorPointY * multiplier)

    var dropTarget: Option[PileGroup] = None
    var dropDistance = 65536

    pile.phaser.getPlaymat.getPiles.values.foreach { p =>
      if (p.id != pile.id) {
        var overlapX = 0
        if ((minX >= p.x && minX <= p.x + p.intersectWidth) || (maxX >= p.x && maxX <= p.x + p.intersectWidth)) {
          overlapX = Math.abs((p.x - xPoint).toInt)
        }
        var overlapY = 0
        if ((minY >= p.y && minY <= p.y + p.intersectHeight) || (maxY >= p.y && maxY <= p.y + p.intersectHeight)) {
          overlapY = Math.abs((p.y - yPoint).toInt)
        }
        if ((overlapX > 0 && overlapY > 0) && (overlapX + overlapY < dropDistance) && isValidMove(pile, p)) {
          dropDistance = overlapX + overlapY
          dropTarget = Some(p)
        }
        //console.log('Pile [' + p.id + '] overlaps [' + overlapX + ', ' + overlapY + ']');
      }
    }
    //console.log('Choosing [' + (dropTarget === null ? 'none' : dropTarget.id) + '] as drop target with distance [' + dropDistance + '].');
    dropTarget
  }

  def dragSlice(pile: PileGroup, card: CardSprite, p: Pointer) {
    pile.dragCards = pile.cards.drop(card.pileIndex)
    pile.dragCards.zipWithIndex.foreach { x =>
      val (c, idx) = x
      CardTweens.tweenPickUp(c)
      c.startDrag(p, idx)
    }
  }

  def endDrag(pile: PileGroup) {
    getDropTarget(pile) match {
      case None => pile.dragCards.foreach { cancelCard =>
        cancelCard.dragIndex = None
        cancelCard.cancelDrag()
      }
      case Some(dropTarget) =>
        // console.log('Moving [' + this.dragCards + '] to [' + dropTarget.id + '].');
        pile.dragCards.foreach { moveCard =>
          moveCard.dragIndex = None
        }
        pile.phaser.sendMove(MoveCards(cards = pile.dragCards.map(_.id), src = pile.id, tgt = dropTarget.id, auto = false))
    }
    pile.dragCards = Seq.empty
  }
}
