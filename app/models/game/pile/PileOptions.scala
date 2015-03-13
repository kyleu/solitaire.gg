package models.game.pile

import models.game.pile.constraints.{DragToConstraint, DragFromConstraint, SelectPileConstraint, SelectCardConstraint}

object PileOptions {
  val empty = PileOptions()
}

case class PileOptions(
  cardsShown: Option[Int] = None,
  direction: Option[String] = None,

  selectCardConstraint: Option[SelectCardConstraint] = None,
  selectPileConstraint: Option[SelectPileConstraint] = None,
  dragFromConstraint: Option[DragFromConstraint] = None,
  dragToConstraint: Option[DragToConstraint] = None
) {
  def combine(po: PileOptions) = {
    this.copy(
      cardsShown = if(po.cardsShown.isDefined) { po.cardsShown } else { cardsShown },
      direction = if(po.direction.isDefined) { po.direction } else { direction },

      selectCardConstraint = if(po.selectCardConstraint.isDefined) { po.selectCardConstraint } else { selectCardConstraint },
      selectPileConstraint = if(po.selectPileConstraint.isDefined) { po.selectPileConstraint } else { selectPileConstraint },
      dragFromConstraint = if(po.dragFromConstraint.isDefined) { po.dragFromConstraint } else { dragFromConstraint },
      dragToConstraint = if(po.dragToConstraint.isDefined) { po.dragToConstraint } else { dragToConstraint }
    )
  }
}
