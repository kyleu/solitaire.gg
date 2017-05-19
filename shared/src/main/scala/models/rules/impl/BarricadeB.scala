package models.rules.impl

import models.rules._

object BarricadeB extends GameRules(
  id = "barricadeb",
  completed = true,
  title = "Barricade B",
  like = Some("block"),
  layout = "s::f|t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(StockRules(dealTo = StockDealTo.Never, maximumDeals = Some(1))),
  foundations = Seq(FoundationRules(numPiles = 8, autoMoveCards = true)),
  tableaus = Seq(
    TableauRules(
      numPiles = 14,
      initialCards = InitialCards.Count(2),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock
    )
  )
)
