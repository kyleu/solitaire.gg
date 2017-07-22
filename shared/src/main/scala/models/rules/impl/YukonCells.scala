package models.rules.impl

import models.rules._

object YukonCells extends GameRules(
  id = "yukoncells",
  completed = false,
  title = "Yukon Cells",
  like = Some("yukon"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/yukon_cells.htm")),
  layout = "f|c|t",
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
    emptyFilledWith = FillEmptyWith.HighRank
  )),
  cells = Some(CellRules(numPiles = 2))
)
