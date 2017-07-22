package models.rules.impl

import models.rules._

object YukonKings extends GameRules(
  id = "yukonkings",
  completed = false,
  title = "Yukon Kings",
  like = Some("yukon"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/yukon_kings.htm")),
  layout = "|t",
  victoryCondition = VictoryCondition.AllOnTableauSorted,
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
    wrap = true,
    suitMatchRuleForMovingStacks = SuitMatchRule.Any,
    rankMatchRuleForMovingStacks = RankMatchRule.Any,
    emptyFilledWith = FillEmptyWith.HighRank
  ))
)
