package models.rules.impl

import models.rules._

object Eliminator extends GameRules(
  id = "eliminator",
  completed = true,
  title = "Eliminator",
  links = Seq(Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Eliminator.html.en")),
  layout = "f|:t",
  foundations = Seq(
    FoundationRules(
      numPiles = 6,
      lowRank = FoundationLowRank.AnyCard,
      suitMatchRule = SuitMatchRule.Any,
      rankMatchRule = RankMatchRule.UpOrDown,
      maxCards = 0,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 4,
      initialCards = InitialCards.Count(13),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      rankMatchRuleForBuilding = RankMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None
    )
  )
)
