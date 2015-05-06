package services.help

import models.game.rules.{ StockCardsDealt, StockDealTo, StockRules }
import utils.NumberUtils

object StockHelpService {
  def stock(rules: StockRules) = {
    val ret = collection.mutable.ArrayBuffer.empty[String]

    val cardsDealt = rules.cardsDealt match {
      case StockCardsDealt.Count(i) => NumberUtils.toWords(i) + " card" + (if(i == 1) { "" } else { "s" })
      case StockCardsDealt.FewerEachTime => "three cards, then fewer each time"
    }

    ret += (rules.dealTo match {
      case StockDealTo.Foundation => "When selected, deals " + cardsDealt + " to each foundation pile."
      case StockDealTo.Manually => "Manually move cards from the stock."
      case StockDealTo.Never => "No cards may be moved."
      case StockDealTo.Reserve => "When selected, deals " + cardsDealt + " to each reserve pile."
      case StockDealTo.Tableau => "When selected, deals " + cardsDealt + " to each tableau pile."
      case StockDealTo.TableauFirstSet => "When selected, deals " + cardsDealt + " to each tableau pile in the first set."
      case StockDealTo.TableauIfNoneEmpty => "When selected, deals " + cardsDealt + " to each tableau pile if none are empty."
      case StockDealTo.TableauNonEmpty => "When selected, deals " + cardsDealt + " to each non-empty tableau pile."
      case StockDealTo.Waste => "When selected, deals " + cardsDealt + " to the waste."
      case StockDealTo.WasteOrPairManually => "When selected, deals " + cardsDealt + " to the waste, or pair manually."
    })

    rules.maximumDeals match {
      case Some(i) => "Up to " + NumberUtils.toWords(i) + " passes through the stock."
      case None => // no op
    }

    if(rules.cardsShown > 1) {
      ret += NumberUtils.toWords(rules.cardsShown, properCase = true) + " cards are visible."
    }

    rules.name -> ret
  }
}
