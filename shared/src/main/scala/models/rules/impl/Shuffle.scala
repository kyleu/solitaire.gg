package models.rules.impl

import models.rules._

object Shuffle extends GameRules(
  id = "shuffle",
  completed = true,
  title = "Shuffle",
  like = Some("neptune"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/shuffle.htm")),
  layout = "s.:f|t",
  cardRemovalMethod = CardRemovalMethod.RemoveConsecutiveRankPairsOrAK,
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Never,
      maximumDeals = Some(1)
    )
  ),
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock,
      emptyFilledWith = FillEmptyWith.None
    )
  )
)
