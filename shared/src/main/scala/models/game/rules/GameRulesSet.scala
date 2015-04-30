package models.game.rules

import models.game.rules.generated.GeneratedGameRules
import models.game.rules.custom.CustomGameRules

object GameRulesSet {
  val all = (GeneratedGameRules.all ++ CustomGameRules.all).sortBy(_.id)
  val allById = all.map(x => x.id -> x).toMap
}
