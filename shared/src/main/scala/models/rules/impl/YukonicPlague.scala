package models.rules.impl

import models.rules._

object YukonicPlague extends GameRules(
  id = "yukonicplague",
  completed = false,
  title = "Yukonic Plague",
  like = Some("yukon"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/yukonic_plague.htm")),
  layout = "f|r|t",
  foundations = IndexedSeq(FoundationRules(numPiles = 4, autoMoveCards = true)),
  tableaus = IndexedSeq(TableauRules(
    initialCards = InitialCards.Custom,
    customInitialCards = Seq(
      "U",
      "DDUU",
      "DDUUU",
      "DDUUUU",
      "DDUUUUU",
      "DDUUUUUU",
      "DDUUUUUU"
    ),
    cardsFaceDown = TableauFaceDownCards.Count(0),
    suitMatchRuleForMovingStacks = SuitMatchRule.Any,
    rankMatchRuleForMovingStacks = RankMatchRule.Any,
    emptyFilledWith = FillEmptyWith.HighRank
  )),
  reserves = Some(ReserveRules(initialCards = 13))
)
