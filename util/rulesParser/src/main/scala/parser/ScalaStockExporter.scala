package parser

import models.game.rules._

object ScalaStockExporter {
  def exportStock(rules: GameRules, ret: StringBuilder): Unit = {
    def add(s: String) = ret ++= s + "\n"

    rules.stock match {
      case Some(s) =>
        add("  stock = Some(")
        add("    StockRules(")
        add(s"""      name = "${s.name.replaceAllLiterally("\"", "")}",""")
        add(s"""      dealTo = StockDealTo.${s.dealTo},""")
        add(s"""      maximumDeals = ${s.maximumDeals},""")
        add(s"""      cardsDealt = StockCardsDealt.${s.cardsDealt},""")
        add(s"""      stopAfterPartialDeal = ${s.stopAfterPartialDeal},""")
        add(s"""      createPocketWhenEmpty = ${s.createPocketWhenEmpty},""")
        add(s"""      galleryMode = ${s.galleryMode}""")
        add("    )")
        add("  ),")
      case None => // no op
    }
  }
}
