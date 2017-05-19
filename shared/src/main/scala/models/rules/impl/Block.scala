package models.rules.impl

import models.rules._

object Block extends GameRules(
  id = "block",
  completed = true,
  title = "Block",
  related = Seq("barricadeb"),
  layout = ":s:f|t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(StockRules(dealTo = StockDealTo.Never, maximumDeals = Some(1))),
  foundations = Seq(FoundationRules(numPiles = 8, autoMoveCards = true)),
  tableaus = Seq(
    TableauRules(
      numPiles = 12,
      initialCards = InitialCards.Count(2),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock
    )
  )
)
