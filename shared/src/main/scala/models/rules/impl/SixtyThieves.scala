package models.rules.impl

import models.rules._

object SixtyThieves extends GameRules(
  id = "sixtythieves",
  completed = true,
  title = "Sixty Thieves",
  like = Some("fortythieves"),
  related = Seq("marierose", "malmaison"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/sixty_thieves.htm")),
  layout = "swf|.:t",
  deckOptions = DeckOptions(numDecks = 3),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 12,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 12,
      initialCards = InitialCards.Count(5),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
