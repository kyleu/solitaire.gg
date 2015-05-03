package services.help

import models.game.rules.CellRules

object CellHelpService {
  def cell(rules: CellRules) = {
    rules.name -> Seq.empty[String]
  }
}
