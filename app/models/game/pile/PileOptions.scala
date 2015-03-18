package models.game.pile

import models.game.pile.actions.{SelectPileAction, SelectCardAction}
import models.game.pile.constraints.{DragToConstraint, DragFromConstraint, SelectPileConstraint, SelectCardConstraint}

object PileOptions {
  val empty = PileOptions()
}

object ClientPileOptions {
  def fromPileOptions(po: PileOptions): ClientPileOptions = ClientPileOptions(po.cardsShown, po.direction, po.dragFromConstraint.map(_.id), po.dragFromConstraint.flatMap(_.clientOptions))
}
case class ClientPileOptions(cardsShown: Option[Int], direction: Option[String], dragFromConstraint: Option[String], dragFromOptions: Option[Map[String, String]])

case class PileOptions(
  cardsShown: Option[Int] = None,
  direction: Option[String] = None,

  selectCardConstraint: Option[SelectCardConstraint] = None,
  selectPileConstraint: Option[SelectPileConstraint] = None,
  dragFromConstraint: Option[DragFromConstraint] = None,
  dragToConstraint: Option[DragToConstraint] = None,

  selectCardAction: Option[SelectCardAction] = None,
  selectPileAction: Option[SelectPileAction] = None
) {
  def combine(po: PileOptions) = {
    this.copy(
      cardsShown = if(po.cardsShown.isDefined) { po.cardsShown } else { cardsShown },
      direction = if(po.direction.isDefined) { po.direction } else { direction },

      selectCardConstraint = if(po.selectCardConstraint.isDefined) { po.selectCardConstraint } else { selectCardConstraint },
      selectPileConstraint = if(po.selectPileConstraint.isDefined) { po.selectPileConstraint } else { selectPileConstraint },
      dragFromConstraint = if(po.dragFromConstraint.isDefined) { po.dragFromConstraint } else { dragFromConstraint },
      dragToConstraint = if(po.dragToConstraint.isDefined) { po.dragToConstraint } else { dragToConstraint },

      selectCardAction = if(po.selectCardAction.isDefined) { po.selectCardAction } else { selectCardAction },
      selectPileAction = if(po.selectPileAction.isDefined) { po.selectPileAction } else { selectPileAction }
    )
  }
}
