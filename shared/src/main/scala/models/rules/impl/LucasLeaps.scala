package models.rules.impl

import models.rules._

object LucasLeaps extends GameRules(
  id = "lucasleaps",
  completed = true,
  title = "Lucas Leaps",
  like = Some("waningmoon"),
  links = Seq(
    Link("PySol", "pysolfc.sourceforge.net/doc/rules/lucas.html"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/lucas.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/lucas.htm")
  ),
  layout = "sw::f|t",
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
      numPiles = 13,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit
    )
  )
)
