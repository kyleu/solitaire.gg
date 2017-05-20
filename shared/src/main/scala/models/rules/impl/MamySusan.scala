package models.rules.impl

import models.rules._

object MamySusan extends GameRules(
  id = "mamysusan",
  completed = true,
  title = "Mamy Susan",
  like = Some("fortythieves"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/mamy_susan.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/MamySusan.htm")
  ),
  layout = "swf|r:t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(StockRules(maximumDeals = Some(1))),
  waste = Some(WasteRules()),
  foundations = Seq(FoundationRules(numPiles = 8, autoMoveCards = true)),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  reserves = Some(ReserveRules(initialCards = 5, cardsFaceDown = -1))
)
