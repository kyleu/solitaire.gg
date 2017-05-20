package models.rules.impl

import models.rules._

object Robie extends GameRules(
  id = "robie",
  completed = true,
  title = "Robie",
  like = Some("fortythieves"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/robie.htm")),
  layout = "swf|.t",
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
      numPiles = 10,
      initialCards = InitialCards.Count(0),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
