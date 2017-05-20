package models.rules.impl

import models.rules._

object TvetesGrandfather extends GameRules(
  id = "tvetesgrandfather",
  completed = true,
  title = "Tvete's Grandfather",
  links = Seq(Link("KPatience", "docs.kde.org/development/en/kdegames/kpat/rules-specific.html#grandfather")),
  layout = ".:f|t",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "D",
        "DDUUUUU",
        "DDDDUUUUU",
        "DDDDDDUUUUU",
        "DDDDDUUUUU",
        "DDDUUUUU",
        "DUUUUU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  ),
  special = Some(
    SpecialRules(
      redealsAllowed = 2
    )
  )
)
