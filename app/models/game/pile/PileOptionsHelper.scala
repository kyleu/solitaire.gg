package models.game.pile

import models.game.pile.actions.{ SelectPileActions, SelectCardActions }
import models.game.pile.constraints.Constraints

object PileOptionsHelper {
  val empty = PileOptions()

  val foundation = PileOptions(
    cardsShown = Some(1),
    dragFromConstraint = Some(Constraints.topCardOnly),
    dragToConstraint = Some(Constraints.klondikeFoundationDragTo)
  )

  def stock(cardsToDraw: Int, drawTo: String, redrawFrom: Option[String]) = PileOptions(
    cardsShown = Some(1),
    selectCardConstraint = Some(Constraints.topCardOnly),
    selectPileConstraint = Some(Constraints.empty),

    selectCardAction = Some(SelectCardActions.drawToPile(cardsToDraw, drawTo, turnFaceUp = true)),
    selectPileAction = redrawFrom match {
      case Some(rf) => Some(SelectPileActions.moveAll(rf))
      case None => None
    }
  )

  val tableau = PileOptions(
    direction = Some("d"),
    selectCardConstraint = Some(Constraints.klondikeSelectCard),
    dragFromConstraint = Some(Constraints.klondikeDragFrom),
    dragToConstraint = Some(Constraints.klondikeTableauDragTo),
    selectCardAction = Some(SelectCardActions.klondike)
  )

  val waste = PileOptions(
    cardsShown = Some(3),
    direction = Some("r"),
    selectCardConstraint = Some(Constraints.topCardOnly),
    dragFromConstraint = Some(Constraints.topCardOnly),
    selectCardAction = Some(SelectCardActions.klondike)
  )
}
