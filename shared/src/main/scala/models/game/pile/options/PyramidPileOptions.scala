package models.game.pile.options

import models.game.Rank
import models.game.pile.actions.{ DragToActions, SelectCardActions }
import models.game.pile.constraints.Constraint
import models.game.rules.{ CardRemovalMethod, PyramidRules }

object PyramidPileOptions {
  def apply(rules: PyramidRules, crm: CardRemovalMethod, lowRank: Rank) = {
    val baseOptions = PileOptions(
      cardsShown = Some(1),
      direction = Some("d"),
      dragFromConstraint = Some(Constraint.topCardOnly),
      dragToConstraint = Some(dragToConstraint(crm, lowRank)),
      selectCardConstraint = Some(Constraint.allOf("top-card-king", Constraint.topCardOnly, selectCardConstraint)),
      selectCardAction = Some(SelectCardActions.drawToPiles(1, Seq("foundation-1"))),
      dragToAction = Some(DragToActions.remove())
    )

    val ret = (1 to rules.height).flatMap { i =>
      (1 to i).map { j =>
        val opts = if (i == 7) {
          baseOptions
        } else {
          optionsFor(baseOptions, "pyramid-" + (i + 1) + "-" + j, "pyramid-" + (i + 1) + "-" + (j + 1))
        }
        (opts, i, j)
      }
    }
    ret
  }

  private[this] def dragToConstraint(crm: CardRemovalMethod, lowRank: Rank) = Constraint("pyramid", (pile, cards, gameState) => {
    val topCard = pile.cards.lastOption.getOrElse(throw new IllegalStateException())
    val firstDraggedCard = cards.headOption.getOrElse(throw new IllegalStateException())
    if (!topCard.u) {
      false
    } else {
      crm match {
        case CardRemovalMethod.BuildSequencesOnFoundation => throw new NotImplementedError(lowRank.toString)
        case CardRemovalMethod.StackSameRankOrSuitInWaste => throw new NotImplementedError(lowRank.toString)
        case _ => crm.canRemove(topCard, firstDraggedCard)
      }
    }
  })

  private[this] val selectCardConstraint = Constraint.specificRanks(Seq(Rank.King))

  private[this] def optionsFor(baseOptions: PileOptions, emptyPiles: String*) = {
    val c = Constraint.pilesEmpty(emptyPiles: _*)
    baseOptions.copy(
      cardsShown = Some(1),
      direction = None,
      dragFromConstraint = Some(c),
      dragToConstraint = Some(Constraint.allOf("pyramid-total", c, baseOptions.dragToConstraint.getOrElse(throw new IllegalStateException()))),
      selectCardConstraint = Some(Constraint.allOf("pyramid-king", c, Constraint.topCardOnly, selectCardConstraint))
    )
  }
}
