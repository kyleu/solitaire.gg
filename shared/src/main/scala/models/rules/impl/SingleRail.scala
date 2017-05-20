package models.rules.impl

import models.rules._

object SingleRail extends GameRules(
  id = "singlerail",
  completed = false,
  title = "Single Rail",
  related = Seq("doublerail"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/single_rail.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/single_rail.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/single_rail.php"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/single-rail.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/SingleRail.htm")
  ),
  layout = "swf|t",
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
      numPiles = 4,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any
    )
  )
)
