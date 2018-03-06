package models.pile.options

import models.pile.actions.{DragToActions, SelectCardActions}
import models.pile.constraints.Constraint
import models.pile.set.PileSet
import models.rules.{CardRemovalMethod, PyramidRules}

object PyramidPileOptions {
  def apply(rules: PyramidRules, crm: CardRemovalMethod) = {
    val baseOptions = PileOptions(
      cardsShown = Some(1),
      direction = Some("d"),
      dragFromConstraint = Some(Constraint.topCardOnly),
      dragToConstraint = Some(dragToConstraint(crm, rules.mayMoveToEmptyFrom, rules.mayMoveToNonEmptyFrom)),
      selectCardConstraint = Some(Constraint.allOf("top-card-select", Constraint.topCardOnly, Constraint.forCardRemovalMethod(crm))),
      selectCardAction = Some(SelectCardActions.drawToPiles(() => 1, Seq("foundation-1"))),
      dragToAction = Some(DragToActions.remove())
    )

    val ret = (1 to rules.height).flatMap { i =>
      (1 to i).map { j =>
        val opts = if (i == rules.height) {
          baseOptions
        } else {
          optionsFor(baseOptions, s"pyramid-${i + 1}-$j", s"pyramid-${i + 1}-${j + 1}")
        }
        (opts, i, j)
      }
    }
    ret
  }

  private[this] def dragToConstraint(crm: CardRemovalMethod, mayMoveToEmptyFrom: Seq[PileSet.Behavior], mayMoveToNonEmptyFrom: Seq[PileSet.Behavior]) = {
    Constraint("pyramid", (src, tgt, cards, _) => tgt.cards.lastOption match {
      case None => false

      case Some(topCard) =>
        val ret = {
          val firstDraggedCard = cards.headOption.getOrElse(throw new IllegalStateException())
          if (!topCard.u) {
            false
          } else {
            crm match {
              case CardRemovalMethod.BuildSequencesOnFoundation => throw new IllegalStateException("BuildSequencesOnFoundation")
              case _ => crm.canRemove(topCard, firstDraggedCard)
            }
          }
        }
        ret && src.pileSet.exists(x => mayMoveToNonEmptyFrom.contains(x.behavior))
    })
  }

  private[this] def optionsFor(baseOptions: PileOptions, emptyPiles: String*) = {
    val c = Constraint.pilesEmpty(emptyPiles: _*)
    baseOptions.copy(
      cardsShown = Some(1),
      direction = None,
      dragFromConstraint = Some(c),
      dragToConstraint = Some(Constraint.allOf("pyramid-total", c, baseOptions.dragToConstraint.getOrElse(
        throw new IllegalStateException()
      ))),
      selectCardConstraint = Some(Constraint.allOf("pyramid-select", c, Constraint.topCardOnly, baseOptions.selectCardConstraint.getOrElse(
        throw new IllegalStateException()
      )))
    )
  }
}
