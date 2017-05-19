package models.rules.impl

import models.rules._

object Dover extends GameRules(
  id = "dover",
  completed = false,
  title = "Dover",
  like = Some("bristol"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/dover.htm")),
  layout = "swf|t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(StockRules(maximumDeals = Some(1))),
  waste = Some(WasteRules(numPiles = 3)),
  foundations = Seq(FoundationRules(numPiles = 8, autoMoveCards = true)),
  tableaus = Seq(TableauRules(
    numPiles = 8,
    initialCards = InitialCards.Count(3),
    cardsFaceDown = TableauFaceDownCards.Count(0),
    suitMatchRuleForBuilding = SuitMatchRule.Any,
    suitMatchRuleForMovingStacks = SuitMatchRule.None,
    mayMoveToEmptyFrom = Seq("stock", "pyramid", "waste", "pocket", "reserve", "cell", "foundation")
  ))
)
