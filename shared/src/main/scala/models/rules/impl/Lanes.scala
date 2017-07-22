package models.rules.impl

import models.rules._

object Lanes extends GameRules(
  id = "lanes",
  completed = true,
  title = "Lanes",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/lanes.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Lanes.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/lanes.htm")
  ),
  layout = "swf|.t",
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 4,
      initialCards = 4,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 6,
      initialCards = InitialCards.Count(3),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
