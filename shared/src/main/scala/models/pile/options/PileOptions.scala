package models.pile.options

import models.pile.actions.{ DragToAction, SelectCardAction, SelectPileAction }
import models.pile.constraints.Constraint

object ClientPileOptions {
  def fromPileOptions(po: PileOptions) = ClientPileOptions(
    po.cardsShown,
    po.direction,
    po.dragFromConstraint.map(_.id).getOrElse("never"),
    po.dragFromConstraint.flatMap(_.clientOptions)
  )
}
case class ClientPileOptions(
  cardsShown: Option[Int] = None,
  direction: Option[String] = None,
  dragFromConstraint: String = "never",
  dragFromOptions: Option[Map[String, String]] = None
)

case class PileOptions(
    cardsShown: Option[Int] = None,
    direction: Option[String] = None,

    selectCardConstraint: Option[Constraint] = None,
    selectPileConstraint: Option[Constraint] = None,
    dragFromConstraint: Option[Constraint] = None,
    dragToConstraint: Option[Constraint] = None,

    selectCardAction: Option[SelectCardAction] = None,
    selectPileAction: Option[SelectPileAction] = None,
    dragToAction: Option[DragToAction] = None
) {
  def combine(other: PileOptions) = PileOptions(
    cardsShown = other.cardsShown.orElse(this.cardsShown),
    direction = other.direction.orElse(this.direction),

    selectCardConstraint = other.selectCardConstraint.orElse(this.selectCardConstraint),
    selectPileConstraint = other.selectPileConstraint.orElse(this.selectPileConstraint),
    dragFromConstraint = other.dragFromConstraint.orElse(this.dragFromConstraint),
    dragToConstraint = other.dragToConstraint.orElse(this.dragToConstraint),

    selectCardAction = other.selectCardAction.orElse(this.selectCardAction),
    selectPileAction = other.selectPileAction.orElse(this.selectPileAction),
    dragToAction = other.dragToAction.orElse(this.dragToAction)
  )
}
