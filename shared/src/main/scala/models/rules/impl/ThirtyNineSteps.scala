package models.rules.impl

import models.rules._

object ThirtyNineSteps extends GameRules(
  id = "thirtyninesteps",
  completed = true,
  title = "Thirty Nine Steps",
  like = Some("waningmoon"),
  related = Seq("lucas"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/thirty_nine_steps.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/thirty-nine-steps.htm")
  ),
  layout = "sw:.f|t",
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 13,
      initialCards = InitialCards.Count(3),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
