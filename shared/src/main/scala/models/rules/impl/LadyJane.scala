package models.rules.impl

import models.rules._

object LadyJane extends GameRules(
  id = "ladyjane",
  completed = true,
  title = "Lady Jane",
  related = Seq("inquisitor"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/lady_jane.htm")),
  layout = "swf|.t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(
    StockRules(
      maximumDeals = Some(2),
      cardsDealt = StockCardsDealt.Count(3)
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
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit
    )
  )
)
