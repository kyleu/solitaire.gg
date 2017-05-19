package models.rules.impl

import models.rules._

object DoubleLimited extends GameRules(
  id = "doublelimited",
  completed = true,
  title = "Double Limited",
  like = Some("limited"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/double_limited.htm")),
  layout = "swf|.:t",
  deckOptions = DeckOptions(numDecks = 4),
  stock = Some(StockRules(maximumDeals = Some(1))),
  waste = Some(WasteRules()),
  foundations = Seq(FoundationRules(numPiles = 16, autoMoveCards = true)),
  tableaus = Seq(TableauRules(
    numPiles = 16,
    initialCards = InitialCards.Count(3),
    cardsFaceDown = TableauFaceDownCards.Count(0),
    suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
    suitMatchRuleForMovingStacks = SuitMatchRule.None
  ))
)
