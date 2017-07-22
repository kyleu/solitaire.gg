package models.rules.impl

import models.rules._

object SuperFlowerGarden extends GameRules(
  id = "superflowergarden",
  completed = false,
  title = "Super Flower Garden",
  like = Some("labellelucie"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/super_flower_gar.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/super_flower_garden.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/super-flower-garden.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/super_flower_garden.php"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/SuperFlowerGarden.htm")
  ),
  layout = "f|t",
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 18,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "U"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
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
