package models.rules.impl

import models.rules._

object Flow extends GameRules(
  id = "flow",
  completed = false,
  title = "Flow",
  like = Some("wavemotion"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/flow.htm")),
  layout = "|tt",
  victoryCondition = VictoryCondition.AllOnTableauSorted,
  tableaus = IndexedSeq(
    TableauRules(
      name = "Reserve",
      numPiles = 8,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None
    ),
    TableauRules(
      setNumber = 1,
      numPiles = 8,
      initialCards = InitialCards.Count(0),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit
    )
  )
)
