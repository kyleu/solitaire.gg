package models.rules.impl

import models.rules._

object Arabella extends GameRules(
  id = "arabella",
  completed = false,
  title = "Arabella",
  related = Seq("doublejane"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/arabella.htm")),
  layout = "swf|t",
  deckOptions = DeckOptions(numDecks = 3),
  stock = Some(StockRules(maximumDeals = Some(1))),
  waste = Some(WasteRules()),
  foundations = Seq(FoundationRules(numPiles = 12, autoMoveCards = true)),
  tableaus = Seq(
    TableauRules(
      numPiles = 13,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
