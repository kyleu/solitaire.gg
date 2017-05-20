package models.rules.impl

import models.rules._

object Isabel extends GameRules(
  id = "isabel",
  completed = true,
  title = "Isabel",
  links = Seq(Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Isabel.html.en")),
  layout = ".::::f|t",
  cardRemovalMethod = CardRemovalMethod.RemovePairsOfSameRank,
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      lowRank = FoundationLowRank.AnyCard,
      maxCards = 0,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 13,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None
    )
  )
)
