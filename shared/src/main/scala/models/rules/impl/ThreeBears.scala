package models.rules.impl

import models.rules._

object ThreeBears extends GameRules(
  id = "threebears",
  completed = false,
  title = "Three Bears",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/three_bears.htm")),
  layout = "swf|t",
  deckOptions = DeckOptions(numDecks = 3),
  stock = Some(
    StockRules(
      cardsDealt = StockCardsDealt.Count(3)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 12,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 13,
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
