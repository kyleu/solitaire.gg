package models.rules.impl

import models.rules._

object Trefoil extends GameRules(
  id = "trefoil",
  completed = false,
  title = "Trefoil",
  like = Some("labellelucie"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/trefoil.htm"),
    Link("Solitaire Till Dawn", "www.semicolon.com/Solitaire/Rules/Trefoil.html"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/trefoil.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/trefoil.php"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/trefoil.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Trefoil.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/trefoil.html")
  ),
  layout = "f|t",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      initialCards = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 16,
      initialCards = InitialCards.Count(3),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None
    )
  ),
  special = Some(
    SpecialRules(
      redealsAllowed = 2
    )
  )
)
