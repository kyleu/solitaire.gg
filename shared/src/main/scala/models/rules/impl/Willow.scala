package models.rules.impl

import models.rules._

object Willow extends GameRules(
  id = "willow",
  completed = true,
  title = "Willow",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/willow.htm")),
  layout = ".:::f|tt",
  foundations = Seq(FoundationRules(numPiles = 4, autoMoveCards = true)),
  tableaus = Seq(
    TableauRules(cardsFaceDown = TableauFaceDownCards.Count(0)),
    TableauRules(
      name = "Fan",
      setNumber = 1,
      numPiles = 4,
      initialCards = InitialCards.Count(6),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      rankMatchRuleForBuilding = RankMatchRule.Equal,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None
    )
  )
)
