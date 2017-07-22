package models.rules.impl

import models.rules._

object MidnightClover extends GameRules(
  id = "midnightclover",
  completed = true,
  title = "Midnight Clover",
  like = Some("fan"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/midnight_clover.htm")),
  layout = "::f|2t",
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 4,
      initialCards = 4,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      name = "Fan",
      numPiles = 16,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  ),
  special = Some(
    SpecialRules(
      drawsAllowed = 1
    )
  )
)
