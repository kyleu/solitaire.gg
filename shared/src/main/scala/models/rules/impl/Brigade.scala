package models.rules.impl

import models.rules._

object Brigade extends GameRules(
  id = "brigade",
  completed = false,
  title = "Brigade",
  like = Some("flowergarden"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/brigade.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/brigade.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/brigade.html")
  ),
  layout = "wf|t",
  waste = Some(
    WasteRules(
      name = "Reserve",
      cardsShown = 20
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      initialCards = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      initialCards = InitialCards.Count(5),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
