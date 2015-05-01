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



  private[this] val baseOptions = PileOptions(
    cardsShown = Some(1),
    direction = Some("r"),
    dragFromConstraint = Some(Constraints.topCardOnly),
    dragToConstraint = Some(Constraints.total(13, aceHigh = false)),
    selectCardConstraint = Some(Constraints.allOf("top-card-king", Constraints.topCardOnly, Constraints.specificRank(King))),
    selectCardAction = Some(SelectCardActions.drawToPiles(1, Seq("foundation-1"))),
    dragToAction = Some(DragToActions.remove())
  )

  private[this] def optionsFor(emptyPiles: String*) = {
    val c = Constraints.pilesEmpty(emptyPiles: _*)
    baseOptions.copy(
      cardsShown = Some(1),
      direction = None,
      dragFromConstraint = Some(c),
      dragToConstraint = Some(Constraints.allOf("pyramid-total", c, Constraints.total(13, aceHigh = false))),
      selectCardConstraint = Some(Constraints.allOf("pyramid-king", c, Constraints.specificRank(King)))
    )
  }
}
