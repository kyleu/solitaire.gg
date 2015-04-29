package models.game.pile.options

import models.game.pile.actions.{ SelectCardActions, SelectPileActions }
import models.game.pile.constraints.Constraints
import models.game.rules.{ StockRules, StockCardsDealt, StockDealTo }

object StockPileOptions {
  def apply(rules: StockRules, pileIdsByType: Map[String, Seq[String]]) = {
    val cardsShown = Some(1)
    val selectCardConstraint = Some(Constraints.topCardOnly)
    val selectPileConstraint = Some(Constraints.empty)

    val cardsToDraw = rules.cardsDealt match {
      case StockCardsDealt.Count(i) => i
      case StockCardsDealt.FewerEachTime => throw new NotImplementedError()
    }

    val drawTo = rules.dealTo match {
      case StockDealTo.Waste => pileIdsByType("waste")
      case StockDealTo.WasteOrPairManually => pileIdsByType("waste") // TODO throw new NotImplementedError()
      case StockDealTo.Foundation => pileIdsByType("foundations")
      case StockDealTo.Tableau => pileIdsByType("tableaus")
      case StockDealTo.TableauFirstSet => throw new NotImplementedError()
      case StockDealTo.TableauIfNoneEmpty => pileIdsByType("tableaus") // TODO throw new NotImplementedError()
      case StockDealTo.TableauNonEmpty => throw new NotImplementedError()
      case StockDealTo.Manually => throw new NotImplementedError()
      case StockDealTo.Never => Nil
      case x => throw new NotImplementedError(x.toString)
    }

    val redrawFrom = rules.maximumDeals match {
      case Some(1) => None
      case None => drawTo.headOption
      case _ => drawTo.headOption // TODO throw new NotImplementedError()
    }

    val selectCardAction = if(drawTo.nonEmpty) {
      Some(SelectCardActions.drawToPiles(cardsToDraw, drawTo, Some(true)))
    } else {
      None
    }
    val selectPileAction = redrawFrom match {
      case Some(rf) => Some(SelectPileActions.moveAll(rf))
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
