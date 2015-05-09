package models.game.pile.options

import models.game.Rank
import models.game.Rank.Ace
import models.game.pile.actions.{ DragToActions, SelectCardActions }
import models.game.pile.constraints.Constraint
import models.game.rules.PyramidRules

object PyramidPileOptions {
  def apply(rules: PyramidRules) = {
    val ret = (1 to rules.height).flatMap { i =>
      (1 to i).map { j =>
        val opts = if (i == 7) {
          baseOptions
        } else {
          optionsFor("pyramid-" + (i + 1) + "-" + j, "pyramid-" + (i + 1) + "-" + (j + 1))
        }
        (opts, i, j)
      }
    }
    ret
  }

  private[this] val dragToConstraint = Constraint("total-" + 13, (pile, cards, gameState) => {
    val aceHigh = false
    if (pile.cards.isEmpty) {
      false
    } else {
      val topCardRank = pile.cards.last.r
      val topCardRankValue = if (topCardRank == Ace && !aceHigh) { 1 } else { topCardRank.value }
      val firstDraggedCardRank = cards.headOption.getOrElse(throw new IllegalStateException()).r
      val firstDraggedCardRankValue = if (firstDraggedCardRank == Ace && !aceHigh) { 1 } else { firstDraggedCardRank.value }
      val totalValue = topCardRankValue + firstDraggedCardRankValue
      totalValue == 13
    }
  })
  private[this] val selectCardConstraint = Constraint("rank-" + Rank.King, (pile, cards, gameState) => {
    cards.tail.isEmpty && cards.headOption.getOrElse(throw new IllegalStateException()).r == Rank.King
  })

  private[this] val baseOptions = PileOptions(
    cardsShown = Some(1),
    direction = Some("d"),
    dragFromConstraint = Some(Constraint.topCardOnly),
    dragToConstraint = Some(dragToConstraint),
    selectCardConstraint = Some(Constraint.allOf("top-card-king", Constraint.topCardOnly, selectCardConstraint)),
    selectCardAction = Some(SelectCardActions.drawToPiles(1, Seq("foundation-1"))),
    dragToAction = Some(DragToActions.remove())
  )

  private[this] def optionsFor(emptyPiles: String*) = {
    val c = Constraint.pilesEmpty(emptyPiles: _*)
    baseOptions.copy(
      cardsShown = Some(1),
      direction = None,
      dragFromConstraint = Some(c),
      dragToConstraint = Some(Constraint.allOf("pyramid-total", c, dragToConstraint)),
      selectCardConstraint = Some(Constraint.allOf("pyramid-king", c, Constraint.topCardOnly, selectCardConstraint))
    )
  }
}
