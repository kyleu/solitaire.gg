package models.rules.impl

import models.rules._

object CastlesInSpain extends GameRules(
  id = "castlesinspain",
  completed = false,
  title = "Castles in Spain",
  like = Some("spanish"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/castles_in_spain.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/castles_in_spain.html"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/castles-in-spain.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/castles_in_spain.php"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/CastlesinSpain.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/castles_in_spain.htm")
  ),
  layout = "f|tt",
  foundations = Seq(FoundationRules(numPiles = 4, autoMoveCards = true)),
  tableaus = Seq(
    TableauRules(
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    ),
    TableauRules(
      setNumber = 1,
      numPiles = 6,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None,
      actionDuringDeal = PileAction.MoveKingsToBottom
    )
  )
)
