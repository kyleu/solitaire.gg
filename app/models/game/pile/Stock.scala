package models.game.pile

import models.game.pile.actions.{SelectPileActions, SelectCardActions}
import models.game.pile.constraints.Constraints

object Stock {
  def options(cardsToDraw: Int, drawTo: String, redrawFrom: Option[String]) = PileOptions(
    cardsShown = Some(1),
    selectCardConstraint = Constraints.topCardOnlySelectCard,
    selectPileConstraint = Constraints.empty,

    selectCardAction = Some(SelectCardActions.drawToPile(cardsToDraw, drawTo, turnFaceUp = true)),
    selectPileAction = redrawFrom match {
      case Some(rf) => Some(SelectPileActions.moveAll(rf))
      case None => None
    }
  )
}

class Stock(id: String, options: PileOptions) extends Pile(id, "stock", options)
