package services.help

import models.game.rules.TableauRules

object TableauHelpService {
  def tableau(rules: TableauRules) = {
    rules.name -> Seq.empty[String]
  }
}
