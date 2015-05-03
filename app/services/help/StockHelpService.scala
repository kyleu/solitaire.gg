package services.help

import models.game.rules.StockRules

object StockHelpService {
  def stock(rules: StockRules) = {
    rules.name -> Seq.empty[String]
  }
}
