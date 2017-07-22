package models.rules.impl

import models.rules._

object Scotch extends GameRules(
  id = "scotch",
  completed = false,
  title = "Scotch",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/scotch_patience.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/scotch_patience.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/scotch_patience.php"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/scotch.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/ScotchPatience.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/scotch_patience.html")
  ),
  layout = "f|t",
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 4,
      suitMatchRule = SuitMatchRule.AlternatingColors,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 18,
      initialCards = InitialCards.Count(3),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None
    )
  )
)
