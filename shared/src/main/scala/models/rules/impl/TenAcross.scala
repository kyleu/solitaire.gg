package models.rules.impl

import models.rules._

object TenAcross extends GameRules(
  id = "tenacross",
  completed = true,
  title = "Ten Across",
  like = Some("russian"),
  links = Seq(Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Ten_Across.html.en")),
  layout = ":.f:c|t",
  foundations = IndexedSeq(FoundationRules(numPiles = 4, autoMoveCards = true)),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "UUUUU",
        "DUUUU",
        "DDUUU",
        "DDDUU",
        "DDDDU",
        "DDDDU",
        "DDDUU",
        "DDUUU",
        "DUUUU",
        "UUUUU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  ),
  cells = Some(CellRules(numPiles = 2, initialCards = 2))
)
