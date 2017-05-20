package models.rules.impl

import models.rules._

object Roosevelt extends GameRules(
  id = "roosevelt",
  completed = false,
  title = "Roosevelt",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/roosevelt.htm")),
  layout = "swf|t",
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
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
