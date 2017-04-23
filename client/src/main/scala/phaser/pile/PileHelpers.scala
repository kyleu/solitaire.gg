package phaser.pile

object PileHelpers {
  def isValidMove(src: PileGroup, tgt: PileGroup) = {
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
    var firstCard = pile.dragCards.head

    var multiplier = 0.9; // Adjust for growth while being dragged.

    var minX = firstCard.x
    var maxX = firstCard.x + (firstCard.width * multiplier)
    var xPoint = firstCard.x + (firstCard.anchorPointX * multiplier)
    var minY = firstCard.y
    var maxY = firstCard.y + (firstCard.height * multiplier)
    var yPoint = firstCard.y + (firstCard.anchorPointY * multiplier)

    var dropTarget: Option[PileGroup] = None
    var dropDistance = 65536

    /* TODO pile.phaser.piles */ Seq.empty[PileGroup].foreach { p =>
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
}
