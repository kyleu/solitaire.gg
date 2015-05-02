package models.game.rules

import models.game.rules.generated.GeneratedGameRules
import models.game.rules.custom.CustomGameRules

object GameRulesSet {
  val all = (GeneratedGameRules.all ++ CustomGameRules.all).sortBy(_.id)
  val allById = all.map(x => x.id -> x).toMap

  val completed = Seq(
    "bakersdozen",
    "canfield",
    "congress",
    "cruel",
    "flowergarden",
    "fourteenout",
    "freecell",
    "golf",
    "gypsy",
    "klondike",
    "klondike1card",
    "missmilligan",
    "nestor",
    "pyramid",
    "spider",
    "trustytwelve",
    "yukon"
  )
  lazy val unfinished = GameRulesSet.all.filter(r => !completed.contains(r.id))
}
