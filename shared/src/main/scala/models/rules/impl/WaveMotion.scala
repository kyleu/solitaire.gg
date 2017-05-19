package models.rules.impl

import models.rules._

object WaveMotion extends GameRules(
  id = "wavemotion",
  completed = false,
  title = "Wave Motion",
  related = Seq("wadingpool", "flow"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/wave_motion.htm")),
  layout = "|tt",
  victoryCondition = VictoryCondition.AllOnTableauSorted,
  tableaus = Seq(
    TableauRules(
      name = "Reserve",
      numPiles = 8,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
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
