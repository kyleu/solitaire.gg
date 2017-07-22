package models.rules.impl

import models.rules._

object Odessa extends GameRules(
  id = "odessa",
  completed = true,
  title = "Odessa",
  like = Some("russian"),
  links = Seq(Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Odessa.html.en")),
  layout = ".:f|t",
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "DDDUUU",
        "DDDUUUUU",
        "DDDUUUUU",
        "DDDUUUUU",
        "DDDUUUUU",
        "DDDUUUUU",
        "DDDUUU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
