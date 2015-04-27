package models.game.pile

import models.game.pile.actions.{ SelectPileActions, SelectCardActions }
import models.game.pile.constraints.Constraints
import models.game.rules.StockCardsDealt.{ FewerEachTime, Count }
import models.game.rules.{ StockCardsDealt, StockDealTo, StockRules }

object StockSet {
  def apply(stockRules: StockRules): StockSet = {
    val options = getOptions(stockRules)
    val piles = Seq(Pile("stock", options))
    new StockSet(piles)
  }

  private[this] def getOptions(stockRules: StockRules) = {
    val cardsToDraw = stockRules.cardsDealt match {
      case StockCardsDealt.Count(i) => i
      case StockCardsDealt.FewerEachTime => 1 // ???
    }

    val drawTo = stockRules.dealTo match {
      case StockDealTo.Waste => "waste-1"
      case StockDealTo.WasteOrPairManually => "waste-1"
      case StockDealTo.Foundation => "foundation-1"
      case StockDealTo.Tableau => "???"
      case StockDealTo.TableauFirstSet => "???"
      case StockDealTo.TableauNonEmpty => "???"
      case StockDealTo.TableauIfNoneEmpty => "???"
      case StockDealTo.Reserve => "???"
      case StockDealTo.Manually => "???"
      case StockDealTo.Never => "???"
      case x => throw new NotImplementedError(x.toString)
    }

    // TODO Support max redraws
    val redrawFrom = Option(drawTo)

    PileOptions(
      cardsShown = Some(1),
      selectCardConstraint = Some(Constraints.topCardOnly),
      selectPileConstraint = Some(Constraints.empty),

      selectCardAction = Some(SelectCardActions.drawToPile(cardsToDraw, drawTo, Some(true))),
      selectPileAction = redrawFrom match {
        case Some(rf) => Some(SelectPileActions.moveAll(rf))
        case None => None
      }
    )
  }
}

class StockSet(piles: Seq[Pile]) extends PileSet("stock", piles)
