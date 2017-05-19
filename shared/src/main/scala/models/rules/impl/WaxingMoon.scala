package models.rules.impl

import models.rules._

object WaxingMoon extends GameRules(
  id = "waxingmoon",
  completed = true,
  title = "Waxing Moon",
  layout = "swf|t",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/waxing_moon.htm")),
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(StockRules(maximumDeals = Some(1))),
  waste = Some(WasteRules()),
  foundations = Seq(FoundationRules(numPiles = 8, autoMoveCards = true)),
  tableaus = Seq(TableauRules(
    numPiles = 12,
    initialCards = InitialCards.Count(4),
    cardsFaceDown = TableauFaceDownCards.Count(0),
    suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
    suitMatchRuleForMovingStacks = SuitMatchRule.None,
    emptyFilledWith = FillEmptyWith.HighRank
  ))
)
