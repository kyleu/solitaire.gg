package phaser.pile

import com.definitelyscala.phaser.Pointer
import models.MC
import models.game.PossibleMove
import phaser.card.{CardSprite, CardTweens}

object PileDragHelper {
  private[this] def isValidMove(src: PileGroup, tgt: PileGroup) = {
    var valid = false
    def check(c: Int, idx: Int) = if (c != src.dragCards(idx).id) { valid = false }

    src.phaser.possibleMoves.foreach { move =>
      if (move.t == PossibleMove.Type.MoveCards && move.sourcePile == src.id && move.targetPile.contains(tgt.id)) {
        if (src.dragCards.length == move.cards.length) {
          valid = true
          move.cards.zipWithIndex.foreach(c => check(c._1, c._2))
        }
      }
    }

    valid
  }

  def getDropTarget(pileGroup: PileGroup) = {
    val firstCard = pileGroup.dragCards.headOption.getOrElse(throw new IllegalStateException)

    val multiplier = 0.9; // Adjust for growth while being dragged.

    val minX = firstCard.x
    val maxX = firstCard.x + (firstCard.width * multiplier)
    val xPoint = firstCard.x + (firstCard.anchorPointX * multiplier)
    val minY = firstCard.y
    val maxY = firstCard.y + (firstCard.height * multiplier)
    val yPoint = firstCard.y + (firstCard.anchorPointY * multiplier)

    var dropTarget: Option[PileGroup] = None
    var dropDistance = 65536

    pileGroup.phaser.getPlaymat.getPileGroups.values.foreach { p =>
      if (p.id != pileGroup.id) {
        var overlapX = 0
        if ((minX >= p.x && minX <= p.x + p.intersectWidth) || (maxX >= p.x && maxX <= p.x + p.intersectWidth)) {
          overlapX = Math.abs((p.x - xPoint).toInt)
        }
        var overlapY = 0
        if ((minY >= p.y && minY <= p.y + p.intersectHeight) || (maxY >= p.y && maxY <= p.y + p.intersectHeight)) {
          overlapY = Math.abs((p.y - yPoint).toInt)
        }
        if ((overlapX > 0 && overlapY > 0) && (overlapX + overlapY < dropDistance) && isValidMove(pileGroup, p)) {
          dropDistance = overlapX + overlapY
          dropTarget = Some(p)
        }
      }
    }
    dropTarget
  }

  def dragSlice(pile: PileGroup, card: CardSprite, p: Pointer) = {
    pile.dragCards = pile.cards.drop(card.pileIndex)
    pile.dragCards.zipWithIndex.foreach { x =>
      val (c, idx) = x
      CardTweens.tweenPickUp(c)
      c.startDrag(p, idx)
    }
  }

  def startDrag(group: PileGroup, sprite: CardSprite, p: Pointer) = {
    group.dragCards = group.cards.drop(sprite.pileIndex)
    group.dragCards.zipWithIndex.foreach { x =>
      val (c, idx) = x
      CardTweens.tweenPickUp(c)
      c.startDrag(p, idx)
    }
  }

  def endDrag(pileGroup: PileGroup) = {
    getDropTarget(pileGroup) match {
      case None => pileGroup.dragCards.foreach { cancelCard =>
        cancelCard.dragIndex = None
        cancelCard.cancelDrag()
      }
      case Some(dropTarget) =>
        // console.log('Moving [' + this.dragCards + '] to [' + dropTarget.id + '].');
        pileGroup.dragCards.foreach { moveCard =>
          moveCard.dragIndex = None
        }
        pileGroup.phaser.sendMove(MC(cards = pileGroup.dragCards.map(_.id), src = pileGroup.id, tgt = dropTarget.id, auto = false))
    }
    pileGroup.dragCards = IndexedSeq.empty
  }
}
