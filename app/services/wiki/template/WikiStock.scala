package services.wiki.template

import models.rules.{StockCardsDealt, StockDealTo, StockRules}
import services.wiki.WikiService
import services.wiki.WikiService.messages

object WikiStock {
  def stock(rules: StockRules) = {
    val ret = collection.mutable.ArrayBuffer.empty[String]

    val cardsDealt = rules.cardsDealt match {
      case StockCardsDealt.Count(i) => if (i == 1) {
        messages("help.stock.cards.dealt.single")
      } else {
        messages("help.stock.cards.dealt.multiple", WikiService.numberAsString(i))
      }
      case StockCardsDealt.FewerEachTime => messages("help.stock.cards.dealt.fewer.each.time")
    }

    ret += (rules.dealTo match {
      case StockDealTo.Foundation => messages("help.stock.deal.to.foundation")
      case StockDealTo.Manually => messages("help.stock.deal.to.manually")
      case StockDealTo.Never => messages("help.stock.deal.to.never")
      case StockDealTo.Reserve => messages("help.stock.deal.to.reserve", cardsDealt)
      case StockDealTo.Tableau => messages("help.stock.deal.to.tableau", cardsDealt)
      case StockDealTo.TableauFirstSet => messages("help.stock.deal.to.tableau.first.set", cardsDealt)
      case StockDealTo.TableauIfNoneEmpty => messages("help.stock.deal.to.tableau.if.none.empty", cardsDealt)
      case StockDealTo.TableauEmpty => messages("help.stock.deal.to.tableau.empty", cardsDealt)
      case StockDealTo.TableauNonEmpty => messages("help.stock.deal.to.tableau.non.empty", cardsDealt)
      case StockDealTo.Waste => messages("help.stock.deal.to.waste", cardsDealt)
      case StockDealTo.WasteOrPairManually => messages("help.stock.deal.to.waste.or.pair.manually", cardsDealt)
    })

    rules.maximumDeals match {
      case Some(i) => i match {
        case 1 => ret += messages("help.stock.max.deals.single")
        case _ => ret += messages("help.stock.max.deals.multiple", WikiService.numberAsString(i))
      }
      case None => ret += messages("help.stock.max.deals.unlimited")
    }

    if (rules.cardsShown > 1) {
      ret += messages("help.stock.cards.shown", WikiService.numberAsString(rules.cardsShown, properCase = true))
    }

    rules.name -> ret
  }
}
