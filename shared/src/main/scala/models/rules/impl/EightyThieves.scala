package models.rules.impl

import models.rules._

object EightyThieves extends GameRules(
  id = "eightythieves",
  completed = false,
  title = "Eighty Thieves",
  like = Some("fortythieves"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/eighty_thieves.htm")),
  layout = "swf|.::::t",
  deckOptions = DeckOptions(numDecks = 4),
  stock = Some(StockRules(maximumDeals = Some(1))),
  waste = Some(WasteRules()),
  foundations = Seq(FoundationRules(numPiles = 16, autoMoveCards = true)),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Count(8),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
