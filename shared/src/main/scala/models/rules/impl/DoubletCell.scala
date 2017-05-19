package models.rules.impl

import models.rules._

object DoubletCell extends GameRules(
  id = "doubletcell",
  completed = true,
  title = "Doublet Cell",
  like = Some("simplepairs"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/doublet_cell.htm")),
  layout = "f::c|:t",
  cardRemovalMethod = CardRemovalMethod.RemovePairsOfSameRankAndColor,
  foundations = Seq(FoundationRules(numPiles = 4, lowRank = FoundationLowRank.AnyCard, maxCards = 0, autoMoveCards = true)),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None
    )
  ),
  cells = Some(CellRules())
)
