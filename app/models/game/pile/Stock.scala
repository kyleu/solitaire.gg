package models.game.pile

import models.game.pile.actions.{SelectPileActions, SelectCardActions}
import models.game.pile.constraints.{SelectPileConstraints, SelectCardConstraints}

object Stock {
  private def defaultOptions(cardsToDraw: Int, drawTo: String, redrawFrom: Option[String]) = PileOptions(
    cardsShown = Some(1),
    selectCardConstraint = Some(SelectCardConstraints.topCardOnly),
    selectPileConstraint = Some(SelectPileConstraints.empty),

    selectCardAction = Some(SelectCardActions.drawToPile(cardsToDraw, drawTo, turnFaceUp = true)),
    selectPileAction = redrawFrom match {
      case Some(rf) => Some(SelectPileActions.moveAll(rf))
      case None => None
    }
  )
}

class Stock(id: String, cardsToDraw: Int = 3, drawTo: String, redrawFrom: Option[String], options: PileOptions = PileOptions.empty) extends Pile(
  id, "stock", Stock.defaultOptions(cardsToDraw, drawTo, redrawFrom).combine(options)
)
