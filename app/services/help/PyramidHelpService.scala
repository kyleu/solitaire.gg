package services.help

import models.game.rules.PyramidRules

object PyramidHelpService {
  def pyramid(rules: PyramidRules) = {
    rules.name -> Seq.empty[String]
  }
}
