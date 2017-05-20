package models.rules.impl

import models.rules._

object SimonSays extends GameRules(
  id = "simonsays",
  completed = false,
  title = "Simon Says",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/simon_says.htm")),
  layout = "|c|t",
  victoryCondition = VictoryCondition.AllOnTableauSorted,
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      wrap = true,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit
    )
  ),
  cells = Some(CellRules(numPiles = 2))
)
