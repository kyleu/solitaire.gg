package parser

import models.game.rules._

object ScalaStockExporter {
  private[this] val defaults = StockRules()

  def exportStock(rules: GameRules, ret: StringBuilder): Unit = {
    def add(s: String) = ret ++= s + "\n"

    rules.stock match {
      case Some(s) =>
        add("  stock = Some(")
        add("    StockRules(")
        val props = collection.mutable.ArrayBuffer.empty[String]
        if(s.name != defaults.name) {
          props += "      name = \"" + s.name.replaceAllLiterally("\"", "") + "\""
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
        add(props.mkString(",\n"))
        add("    )")
        add("  ),")
      case None => // no op
    }
  }
}
