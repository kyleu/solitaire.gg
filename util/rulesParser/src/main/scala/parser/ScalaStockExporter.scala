package parser

import models.rules._

object ScalaStockExporter {
  private[this] val defaults = StockRules()

  def exportStock(rules: GameRules) = {
    rules.stock match {
      case Some(s) =>
        val props = collection.mutable.ArrayBuffer.empty[String]
        if(s.name != defaults.name) {
          props += "      name = \"" + s.name.replaceAllLiterally("\"", "") + "\""
        }
        if(s.cardsShown != defaults.cardsShown) {
          props += "      cardsShown = " + s.cardsShown
        }
        if(s.dealTo != defaults.dealTo) {
          props += "      dealTo = StockDealTo." + s.dealTo
        }
        if(s.maximumDeals != defaults.maximumDeals) {
          props += "      maximumDeals = " + s.maximumDeals
        }
        if(s.cardsDealt != defaults.cardsDealt) {
          props += "      cardsDealt = StockCardsDealt." + s.cardsDealt
        }
        if(s.stopAfterPartialDeal != defaults.stopAfterPartialDeal) {
          props += "      stopAfterPartialDeal = " + s.stopAfterPartialDeal
        }
        if(s.createPocketWhenEmpty != defaults.createPocketWhenEmpty) {
          props += "      createPocketWhenEmpty = " + s.createPocketWhenEmpty
        }
        if(s.galleryMode != defaults.galleryMode) {
          props += "      galleryMode = " + s.galleryMode
        }

        Some(if(props.isEmpty) {
          "  stock = Some(StockRules())"
        } else {
          "  stock = Some(\n" +
          "    StockRules(\n" +
          props.mkString(",\n") +
          "\n" +
          "    )\n" +
          "  )"
        })
      case None => None
    }
  }
}
