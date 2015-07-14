package models.rules.impl

import models.card.Rank
import models.rules._

object Sandbox extends GameRules(
  id = "sandbox",
  custom = true,
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
