package services.help

import models.rules.{ StockCardsDealt, StockDealTo, StockRules }
import play.api.i18n.Messages
import utils.NumberUtils

object StockHelpService {
  def stock(rules: StockRules)(implicit messages: Messages) = {
    val ret = collection.mutable.ArrayBuffer.empty[String]

    val cardsDealt = rules.cardsDealt match {
      case StockCardsDealt.Count(i) => if (i == 1) {
        Messages("help.stock.cards.dealt.single")
      } else {
        Messages("help.stock.cards.dealt.multiple", NumberUtils.toWords(i))
      }
      case StockCardsDealt.FewerEachTime => Messages("help.stock.cards.dealt.fewer.each.time")
    }

    ret += (rules.dealTo match {
      case StockDealTo.Foundation => Messages("help.stock.deal.to.foundation")
      case StockDealTo.Manually => Messages("help.stock.deal.to.manually")
      case StockDealTo.Never => Messages("help.stock.deal.to.never")
      case StockDealTo.Reserve => Messages("help.stock.deal.to.reserve", cardsDealt)
      case StockDealTo.Tableau => Messages("help.stock.deal.to.tableau", cardsDealt)
      case StockDealTo.TableauFirstSet => Messages("help.stock.deal.to.tableau.first.set", cardsDealt)
      case StockDealTo.TableauIfNoneEmpty => Messages("help.stock.deal.to.tableau.if.none.empty", cardsDealt)
      case StockDealTo.TableauEmpty => Messages("help.stock.deal.to.tableau.empty", cardsDealt)
      case StockDealTo.TableauNonEmpty => Messages("help.stock.deal.to.tableau.non.empty", cardsDealt)
      case StockDealTo.Waste => Messages("help.stock.deal.to.waste", cardsDealt)
      case StockDealTo.WasteOrPairManually => Messages("help.stock.deal.to.waste.or.pair.manually", cardsDealt)
    })

    rules.maximumDeals match {
      case Some(i) => i match {
        case 1 => ret += Messages("help.stock.max.deals.single")
        case _ => ret += Messages("help.stock.max.deals.multiple", NumberUtils.toWords(i))
      }
      case None => ret += Messages("help.stock.max.deals.unlimited")
    }

    if (rules.cardsShown > 1) {
      ret += Messages("help.stock.cards.shown", NumberUtils.toWords(rules.cardsShown, properCase = true))
    }

    rules.name -> ret
  }
}
