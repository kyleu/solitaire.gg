package models.rules.impl

import models.rules._

object McClellan extends GameRules(
  id = "mcclellan",
  completed = true,
  title = "McClellan",
  like = Some("littlenapoleon"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/mcclellan.htm")),
  layout = "swf|.:t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(4),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit
    )
  )
)
