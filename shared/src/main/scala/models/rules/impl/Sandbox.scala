package models.rules.impl

import models.card.Rank
import models.rules._

object Sandbox extends GameRules(
  id = "sandbox",
  completed = false,
  title = "Sandbox",
  layout = "s|f",
  cardRemovalMethod = CardRemovalMethod.BuildSequencesOnFoundation,

  deckOptions = DeckOptions(ranks = Seq(Rank.Ace)),

  foundations = IndexedSeq(FoundationRules(
    suitMatchRule = SuitMatchRule.Any,
    rankMatchRule = RankMatchRule.Any
  )),
  stock = Some(StockRules(
    dealTo = StockDealTo.Foundation
  ))
)
