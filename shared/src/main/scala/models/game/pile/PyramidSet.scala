package models.game.pile

import models.game.Rank.King
import models.game.pile.actions.{ SelectCardActions, DragToActions }
import models.game.pile.constraints.Constraints
import models.game.rules.PyramidRules

object PyramidSet {
  private[this] val options = PileOptionsHelper.waste.combine(PileOptions(
    dragFromConstraint = Some(Constraints.topCardOnly),
    dragToConstraint = Some(Constraints.total(13, aceHigh = false)),
    selectCardConstraint = Some(Constraints.allOf("top-card-king", Constraints.topCardOnly, Constraints.specificRank(King))),
    selectCardAction = Some(SelectCardActions.drawToPile(1, "graveyard")),
    dragToAction = Some(DragToActions.remove())
  ))

  private[this] def optionsFor(emptyPiles: String*) = {
    val c = Constraints.pilesEmpty(emptyPiles: _*)
    options.copy(
      cardsShown = Some(1),
      direction = None,
      dragFromConstraint = Some(c),
      dragToConstraint = Some(Constraints.allOf("pyramid-total", c, Constraints.total(13, aceHigh = false))),
      selectCardConstraint = Some(Constraints.allOf("pyramid-king", c, Constraints.specificRank(King)))
    )
  }

  def apply(pyramidRules: PyramidRules): PyramidSet = {
    val prefix = "pyramid-"
    val piles = (1 to pyramidRules.height).flatMap { i =>
      (1 to i).map { j =>
        val o = if (i == 7) { options } else { optionsFor("pile-" + (i + 1) + "-" + j, "pile-" + (i + 1) + "-" + (j + 1)) }
        Pile(prefix + i + "-" + j, o)
      }
    }
    new PyramidSet(piles)
  }
}

class PyramidSet(piles: Seq[Pile]) extends PileSet("pyramid", piles)
