package models.rules.impl

import models.rules._

object KingCell extends GameRules(
  id = "kingcell",
  completed = false,
  title = "KingCell",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/kingcell.htm")),
  layout = "f|c|t",
  foundations = IndexedSeq(FoundationRules(numPiles = 4, autoMoveCards = true)),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  ),
  cells = Some(CellRules())
)
