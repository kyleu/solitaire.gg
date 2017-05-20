package models.rules.impl

import models.rules._

object ThirtySix extends GameRules(
  id = "thirtysix",
  completed = true,
  title = "Thirty Six",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/thirty_six.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/thirty_six.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/thirty-six.php"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/thirty-six.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/ThirtySix.htm"),
    Link("Jan Wolter's Experiments", "/article/thirtysix.html")
  ),
  layout = "swf|.t",
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
      numPiles = 6,
      initialCards = InitialCards.Count(6),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any
    )
  )
)
