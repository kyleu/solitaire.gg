package models.rules.impl

import models.rules._

object DoubleTrigon extends GameRules(
  id = "doubletrigon",
  completed = true,
  title = "Double Trigon",
  like = Some("trigon"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/double_trigon.htm")),
  layout = "swf|:t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(StockRules()),
  waste = Some(WasteRules()),
  foundations = Seq(FoundationRules(numPiles = 8, autoMoveCards = true)),
  tableaus = Seq(TableauRules(
    numPiles = 9,
    suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
    suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
    emptyFilledWith = FillEmptyWith.HighRank
  ))
)
