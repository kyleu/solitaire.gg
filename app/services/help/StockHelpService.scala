package services.help

import models.game.rules.{ StockCardsDealt, StockDealTo, StockRules }
import utils.NumberUtils

object StockHelpService {
  def stock(rules: StockRules) = {
    val ret = collection.mutable.ArrayBuffer.empty[String]

    val cardsDealt = rules.cardsDealt match {
      case StockCardsDealt.Count(i) => NumberUtils.toWords(i) + " cards"
      case StockCardsDealt.FewerEachTime => "three cards, then fewer each time"
    }

    ret += (rules.dealTo match {
      case StockDealTo.Foundation => "Deals " + cardsDealt + " to the foundation piles."
      case StockDealTo.Manually => "Manually move cards from the stock."
      case StockDealTo.Never => "No cards may be moved."
      case StockDealTo.Reserve => "Deals " + cardsDealt + " to the reserve piles."
      case StockDealTo.Tableau => "Deals " + cardsDealt + " to the tableau piles."
      case StockDealTo.TableauFirstSet => "Deals " + cardsDealt + " to the first set of tableau piles."
      case StockDealTo.TableauIfNoneEmpty => "Deals " + cardsDealt + " to the tableau piles if none are empty."
      case StockDealTo.TableauNonEmpty => "Deals " + cardsDealt + " to the non-empty tableau piles."
      case StockDealTo.Waste => "Deals " + cardsDealt + " to the waste."
      case StockDealTo.WasteOrPairManually => "Deals " + cardsDealt + " to the waste, or pair manually."
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
