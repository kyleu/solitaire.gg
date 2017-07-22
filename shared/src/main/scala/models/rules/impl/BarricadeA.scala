package models.rules.impl

import models.rules._

object BarricadeA extends GameRules(
  id = "barricadea",
  completed = true,
  title = "Barricade A",
  links = Seq(Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/barricade.htm")),
  layout = ":s:f|t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(StockRules(dealTo = StockDealTo.Manually, maximumDeals = Some(1))),
  foundations = IndexedSeq(FoundationRules(numPiles = 8, suitMatchRule = SuitMatchRule.Any, autoMoveCards = true)),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 12,
      initialCards = InitialCards.Count(3),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock
    )
  )
)
