package models.game.pile

import models.game.pile.actions.{ SelectPileActions, SelectCardActions }
import models.game.pile.constraints.Constraints

object PileOptionsHelper {
  val foundation = PileOptions(
    cardsShown = Some(1),
    dragFromConstraint = Constraints.topCardOnly,
    dragToConstraint = Constraints.klondikeFoundationDragTo
  )

  def stock(cardsToDraw: Int, drawTo: String, redrawFrom: Option[String]) = PileOptions(
    cardsShown = Some(1),
    selectCardConstraint = Constraints.topCardOnly,
    selectPileConstraint = Constraints.empty,

    selectCardAction = Some(SelectCardActions.drawToPile(cardsToDraw, drawTo, turnFaceUp = true)),
    selectPileAction = redrawFrom match {
      case Some(rf) => Some(SelectPileActions.moveAll(rf))
      case None => None
    }
  )

  val tableau = PileOptions(
    direction = Some("d"),
    selectCardConstraint = Constraints.klondikeSelectCard,
    dragFromConstraint = Constraints.klondikeDragFrom,
    dragToConstraint = Constraints.klondikeTableauDragTo,
    selectCardAction = Some(SelectCardActions.klondike)
  )

  val waste = PileOptions(
    cardsShown = Some(3),
    direction = Some("r"),
    selectCardConstraint = Constraints.topCardOnly,
    dragFromConstraint = Constraints.topCardOnly,
    selectCardAction = Some(SelectCardActions.klondike)
  )
}
