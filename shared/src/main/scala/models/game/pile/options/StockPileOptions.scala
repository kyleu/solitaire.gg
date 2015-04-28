package models.game.pile.options

import models.game.pile.actions.{ SelectCardActions, SelectPileActions }
import models.game.pile.constraints.Constraints
import models.game.rules.{ StockRules, StockCardsDealt, StockDealTo }

object StockPileOptions {
  def apply(rules: StockRules) = {
    val cardsShown = Some(1)
    val selectCardConstraint = Some(Constraints.topCardOnly)
    val selectPileConstraint = Some(Constraints.empty)

    val cardsToDraw = rules.cardsDealt match {
      case StockCardsDealt.Count(i) => i
      case StockCardsDealt.FewerEachTime => throw new NotImplementedError()
    }

    val drawTo = rules.dealTo match {
      case StockDealTo.Waste => "waste-1"
      case _ => throw new NotImplementedError()
    }

    val redrawFrom = Option(drawTo)

    val selectCardAction = Some(SelectCardActions.drawToPiles(cardsToDraw, Seq(drawTo), Some(true)))
    val selectPileAction = redrawFrom match {
      case Some(rf) => Some(SelectPileActions.moveAll(drawTo))
      case None => None
    }

    PileOptions(
      cardsShown = cardsShown,
      selectCardConstraint = selectCardConstraint,
      selectPileConstraint = selectPileConstraint,
      selectCardAction = selectCardAction,
      selectPileAction = selectPileAction
    )
  }
}
