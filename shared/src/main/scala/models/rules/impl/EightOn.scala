package models.rules.impl

import models.rules._

object EightOn extends GameRules(
  id = "eighton",
  completed = true,
  title = "Eight On",
  like = Some("eightoff"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/eight_on.htm")),
  layout = "::f|c|t",
  foundations = Seq(FoundationRules(numPiles = 4, autoMoveCards = true)),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.HighRank,
      pilesWithLowCardsAtBottom = 4
    )
  ),
  cells = Some(CellRules(numPiles = 8, initialCards = 8))
)
