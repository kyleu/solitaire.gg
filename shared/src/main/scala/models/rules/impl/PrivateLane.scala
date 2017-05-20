package models.rules.impl

import models.rules._

object PrivateLane extends GameRules(
  id = "privatelane",
  completed = true,
  title = "Private Lane",
  like = Some("streetsandalleys"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/private_lane.htm")),
  layout = "f::c|t",
  foundations = Seq(FoundationRules(numPiles = 4, autoMoveCards = true)),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  cells = Some(CellRules(numPiles = 2))
)
