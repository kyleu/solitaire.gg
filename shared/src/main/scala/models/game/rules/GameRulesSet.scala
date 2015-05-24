package models.game.rules

import models.game.pile.Layouts
import models.game.rules.generated.GeneratedGameRules
import models.game.rules.custom.CustomGameRules

object GameRulesSet {
  private[this] val completedIds = Seq(
    "alexanderthegreat",
    "canfield",
    "doubleklondike",
    "fourteenout",
    "freecell",
    "goldmine",
    "golf",
    "gypsy",
    "kingsly",
    "klondike",
    "klondike1card",
    "nestor",
    "pyramid",
    "royalrendezvous",
    "spider",
    "tarantula",
    "trigon",
    "tripleklondike",
    "whitehorse",
    "zerline"
  )

  val all = (GeneratedGameRules.all ++ CustomGameRules.all).sortBy(_.id)
  val allById = all.map(x => x.id -> x).toMap

  val completed = GameRulesSet.all.filter(r => completedIds.contains(r.id))
  val inProgress = Layouts.layouts.keys.toList.sorted.map(r => GameRulesSet.allById(r)).filterNot(r => completed.contains(r))
  lazy val unfinished = GameRulesSet.all.filterNot(r => completed.contains(r) || inProgress.contains(r))
}
