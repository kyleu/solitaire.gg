package services.help

import models.game.rules.ReserveRules

object ReserveHelpService {
  def reserve(rules: ReserveRules) = {
    rules.name -> Seq.empty[String]
  }
}
