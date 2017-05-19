package models.rules.impl

import models.rules._

object Darwin extends GameRules(
  id = "darwin",
  completed = false,
  title = "Darwin",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/darwin.htm")),
  layout = "swf|t",
  deckOptions = DeckOptions(numDecks = 3),
  stock = Some(StockRules(maximumDeals = Some(1))),
  waste = Some(WasteRules()),
  foundations = Seq(FoundationRules(numPiles = 12, autoMoveCards = true)),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Count(8),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      emptyFilledWith = FillEmptyWith.HighRankUntilStockEmpty
    )
  )
)
