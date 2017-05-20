package models.rules.impl

import models.rules._

object SevenBySeven extends GameRules(
  id = "sevenbyseven",
  completed = false,
  title = "Seven by Seven",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/seven_by_seven.htm")),
  layout = "f|c|t",
  foundations = Seq(FoundationRules(numPiles = 4, autoMoveCards = true)),
  tableaus = Seq(
    TableauRules(
      initialCards = InitialCards.Count(7),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      rankMatchRuleForBuilding = RankMatchRule.UpOrDown,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  cells = Some(CellRules(numPiles = 3, initialCards = 3)),
  special = Some(SpecialRules(redealsAllowed = 2))
)
