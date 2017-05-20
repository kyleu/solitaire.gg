package models.rules.impl

import models.rules._

object Somerset extends GameRules(
  id = "somerset",
  completed = true,
  title = "Somerset",
  related = Seq("morehead", "usk"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/somerset.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/somerset.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/somerset.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/somerset.php"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Somerset.htm"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/klondike/somerset.htm")
  ),
  layout = ":::f|t",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "U",
        "UU",
        "UUU",
        "UUUU",
        "UUUUU",
        "UUUUUU",
        "UUUUUUU",
        "UUUUUUUU",
        "UUUUUUUU",
        "UUUUUUUU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
