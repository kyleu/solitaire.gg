package models.game.pile

import models.game.rules.StockCardsDealt.Count
import models.game.rules.{ StockDealTo, StockRules }

object StockSet {
  def apply(stockRules: StockRules): StockSet = {
    val cardsToDraw = stockRules.cardsDealt match {
      case Count(i) => i
      case _ => throw new NotImplementedError()
    }

    val drawTo = stockRules.dealTo match {
      case StockDealTo.Waste => "waste-1"
      case StockDealTo.Foundation => "foundation-1"
      case StockDealTo.TableauIfNoneEmpty => "???"
      case StockDealTo.Never => "???"
      case _ => throw new NotImplementedError()
    }

    // TODO Support max redraws
    val redrawFrom = Some(drawTo)

    val options = PileOptionsHelper.stock(cardsToDraw, drawTo, redrawFrom)
    val piles = Seq(Pile("stock", options))
    new StockSet(piles)
  }
}

class StockSet(piles: Seq[Pile]) extends PileSet("stock", piles)
