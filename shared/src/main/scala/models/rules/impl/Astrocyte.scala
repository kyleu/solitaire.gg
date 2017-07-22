package models.rules.impl

import models.rules._

object Astrocyte extends GameRules(
  id = "astrocyte",
  completed = false,
  title = "Astrocyte",
  like = Some("spider"),
  layout = "sf|c|t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(StockRules(dealTo = StockDealTo.TableauIfNoneEmpty, maximumDeals = Some(1))),
  foundations = IndexedSeq(FoundationRules(numPiles = 8, moveCompleteSequencesOnly = true)),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(8),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit
    )
  ),
  cells = Some(CellRules())
)
