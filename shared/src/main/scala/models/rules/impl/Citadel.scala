package models.rules.impl

import models.rules._

object Citadel extends GameRules(
  id = "citadel",
  completed = false,
  title = "Citadel",
  like = Some("beleagueredcastle"),
  related = Seq("exiledkings"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/citadel.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/citadel.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/citadel.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Citadel.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/citadel.php")
  ),
  layout = "f|t",
  foundations = Seq(FoundationRules(numPiles = 4, initialCards = 4, autoMoveCards = true)),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(6),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      actionDuringDeal = PileAction.MoveToFoundation
    )
  )
)
