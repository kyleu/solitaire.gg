package models.game.rules

import models.game.pile.Layouts
import models.game.rules.generated.GeneratedGameRules
import models.game.rules.custom.CustomGameRules

object GameRulesSet {
  val all = (GeneratedGameRules.all ++ CustomGameRules.all).sortBy(_.id)
  val allById = all.map(x => x.id -> x).toMap

  val completed = Layouts.layouts.keys.toList.sorted
  lazy val unfinished = GameRulesSet.all.filter(r => !completed.contains(r.id))
}
