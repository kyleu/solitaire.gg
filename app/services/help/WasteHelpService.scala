package services.help

import models.game.rules.WasteRules

object WasteHelpService {
  def waste(rules: WasteRules) = {
    rules.name -> Seq.empty[String]
  }
}
