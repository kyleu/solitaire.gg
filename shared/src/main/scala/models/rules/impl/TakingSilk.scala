package models.rules.impl

import models.rules._

object TakingSilk extends GameRules(
  id = "takingsilk",
  completed = false,
  title = "Taking Silk",
  related = Seq("floradora"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/taking_silk.htm")),
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
      numPiles = 6,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any
    )
  )
)
