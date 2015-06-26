package models.game.rules.impl

import models.game.Rank
import models.game.rules._

object Sandbox extends GameRules(
  id = "sandbox",
  title = "Sandbox",
  description = "A work in progress...",
  layout = Some("s:f"),
  cardRemovalMethod = CardRemovalMethod.BuildSequencesOnFoundation,

  deckOptions = DeckOptions(ranks = Seq(Rank.Ace)),

  foundations = Seq(FoundationRules(
    suitMatchRule = SuitMatchRule.Any,
    rankMatchRule = RankMatchRule.Any
  )),
  stock = Some(StockRules(
    dealTo = StockDealTo.Foundation
  ))
)
