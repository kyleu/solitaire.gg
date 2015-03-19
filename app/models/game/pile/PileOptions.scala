package models.game.pile

import models.game.pile.actions.{DragToAction, SelectPileAction, SelectCardAction}
import models.game.pile.constraints.{Constraints, Constraint}

object ClientPileOptions {
  def fromPileOptions(po: PileOptions): ClientPileOptions = ClientPileOptions(po.cardsShown, po.direction, po.dragFromConstraint.id, po.dragFromConstraint.clientOptions)
}
case class ClientPileOptions(cardsShown: Option[Int], direction: Option[String], dragFromConstraint: String, dragFromOptions: Option[Map[String, String]])

case class PileOptions(
  cardsShown: Option[Int] = None,
  direction: Option[String] = None,

  selectCardConstraint: Constraint = Constraints.never,
  selectPileConstraint: Constraint = Constraints.never,
  dragFromConstraint: Constraint = Constraints.never,
  dragToConstraint: Constraint = Constraints.never,

  selectCardAction: Option[SelectCardAction] = None,
  selectPileAction: Option[SelectPileAction] = None,
  dragToAction: Option[DragToAction] = None
) {
  def combine(
    cardsShown: Option[Int] = None,
    direction: Option[String] = None,

    selectCardConstraint: Option[Constraint] = None,
    selectPileConstraint: Option[Constraint] = None,
    dragFromConstraint: Option[Constraint] = None,
    dragToConstraint: Option[Constraint] = None,

    selectCardAction: Option[SelectCardAction] = None,
    selectPileAction: Option[SelectPileAction] = None,
    dragToAction: Option[DragToAction] = None
  ) = PileOptions(
    cardsShown = cardsShown.orElse(this.cardsShown),
    direction = direction.orElse(this.direction),

    selectCardConstraint = selectCardConstraint.getOrElse(this.selectCardConstraint),
    selectPileConstraint = selectPileConstraint.getOrElse(this.selectPileConstraint),
    dragFromConstraint = dragFromConstraint.getOrElse(this.dragFromConstraint),
    dragToConstraint = dragToConstraint.getOrElse(this.dragToConstraint),

    selectCardAction = selectCardAction.orElse(this.selectCardAction),
    selectPileAction = selectPileAction.orElse(this.selectPileAction),
    dragToAction = dragToAction.orElse(this.dragToAction)
  )
}
