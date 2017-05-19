package models.rules.impl

import models.rules._

object FortyBandits extends GameRules(
  id = "fortybandits",
  completed = false,
  title = "Forty Bandits",
  like = Some("fortythieves"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/forty_thieves.htm")),
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
      numPiles = 10,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit
    )
  )
)
