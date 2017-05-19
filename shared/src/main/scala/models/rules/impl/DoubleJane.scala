package models.rules.impl

import models.rules._

object DoubleJane extends GameRules(
  id = "doublejane",
  completed = true,
  title = "Double Jane",
  like = Some("arabella"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/double_jane.htm")),
  layout = "swf|:::t",
  deckOptions = DeckOptions(numDecks = 4),
  stock = Some(StockRules(maximumDeals = Some(1))),
  waste = Some(WasteRules()),
  foundations = Seq(FoundationRules(numPiles = 16, autoMoveCards = true)),
  tableaus = Seq(TableauRules(
    numPiles = 13,
    cardsFaceDown = TableauFaceDownCards.Count(0),
    suitMatchRuleForBuilding = SuitMatchRule.Any,
    suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
    emptyFilledWith = FillEmptyWith.HighRank
  ))
)
