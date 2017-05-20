package models.rules.impl

import models.rules._

object ThievesRush extends GameRules(
  id = "thievesrush",
  completed = true,
  title = "Thieves Rush",
  like = Some("fortythieves"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/thieves_rush.htm")),
  layout = "swf|.t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(
    StockRules(
      maximumDeals = Some(3),
      cardsDealt = StockCardsDealt.FewerEachTime
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
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
