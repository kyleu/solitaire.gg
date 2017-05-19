package models.rules.impl

import models.rules._

object BrownRecluse extends GameRules(
  id = "brownrecluse",
  completed = true,
  title = "Brown Recluse",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/brown_recluse.htm")),
  layout = "sw|t",
  victoryCondition = VictoryCondition.AllOnTableauSorted,
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(StockRules(maximumDeals = Some(1))),
  waste = Some(WasteRules()),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      wrap = true,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.WasteThenStock,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
