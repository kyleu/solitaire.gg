package models.rules.impl

import models.rules._

object FourLeafClovers extends GameRules(
  id = "fourleafclovers",
  completed = true,
  title = "Four Leaf Clovers",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/four_leaf_clovers.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/four_leaf_clovers.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/four-leaf-clovers.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/FourLeafClovers.htm")
  ),
  layout = "::::::f|t",
  foundations = Seq(
    FoundationRules(
      suitMatchRule = SuitMatchRule.Any,
      maxCards = 0,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 13,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      rankMatchRuleForBuilding = RankMatchRule.UpOrDown,
      wrap = true,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None
    )
  )
)
