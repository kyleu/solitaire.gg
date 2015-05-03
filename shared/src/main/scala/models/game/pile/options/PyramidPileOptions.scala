package models.game.pile.options

import models.game.Rank.King
import models.game.pile.actions.{ DragToActions, SelectCardActions }
import models.game.pile.constraints.Constraints
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

  private[this] val dragToConstraint = Constraints.total(13, aceHigh = false)
  private[this] val selectCardConstraint = Constraints.specificRank(King)

  private[this] val baseOptions = PileOptions(
    cardsShown = Some(1),
    direction = Some("d"),
    dragFromConstraint = Some(Constraints.topCardOnly),
    dragToConstraint = Some(dragToConstraint),
    selectCardConstraint = Some(Constraints.allOf("top-card-king", Constraints.topCardOnly, selectCardConstraint)),
    selectCardAction = Some(SelectCardActions.drawToPiles(1, Seq("foundation-1"))),
    dragToAction = Some(DragToActions.remove())
  )

  private[this] def optionsFor(emptyPiles: String*) = {
    val c = Constraints.pilesEmpty(emptyPiles: _*)
    baseOptions.copy(
      cardsShown = Some(1),
      direction = None,
      dragFromConstraint = Some(c),
      dragToConstraint = Some(Constraints.allOf("pyramid-total", c, dragToConstraint)),
      selectCardConstraint = Some(Constraints.allOf("pyramid-king", c, Constraints.topCardOnly, selectCardConstraint))
    )
  }
}
