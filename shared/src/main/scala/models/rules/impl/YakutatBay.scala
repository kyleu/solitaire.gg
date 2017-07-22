package models.rules.impl

import models.rules._

object YakutatBay extends GameRules(
  id = "yakutatbay",
  completed = true,
  title = "Yakutat Bay",
  like = Some("yukon"),
  layout = ".:f|t",
  foundations = IndexedSeq(FoundationRules(numPiles = 4, autoMoveCards = true)),
  tableaus = IndexedSeq(TableauRules(
    initialCards = InitialCards.Custom,
    customInitialCards = Seq(
      "U",
      "DUUUUU",
      "DDUUUUU",
      "DDDUUUUU",
      "DDDDUUUUU",
      "DDDDDUUUUU",
      "DDDDDDUUUUU"
    ),
    cardsFaceDown = TableauFaceDownCards.Count(0),
    suitMatchRuleForMovingStacks = SuitMatchRule.Any,
    rankMatchRuleForMovingStacks = RankMatchRule.Any,
    autoFillEmptyFrom = TableauAutoFillEmptyFrom.NextPile,
    emptyFilledWith = FillEmptyWith.HighRank
  ))
)
