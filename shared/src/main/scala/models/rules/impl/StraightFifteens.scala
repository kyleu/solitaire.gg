package models.rules.impl

import models.rules._

object StraightFifteens extends GameRules(
  id = "straightfifteens",
  completed = false,
  title = "Straight Fifteens",
  like = Some("simplepairs"),
  related = Seq("fifteens"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/fifteens.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/fifteens_v2.html")
  ),
  layout = "sf|t",
  cardRemovalMethod = CardRemovalMethod.RemoveSetsAddingToFifteenOr10JQK,
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Never,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      lowRank = FoundationLowRank.AnyCard,
      maxCards = 0,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 16,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock,
      emptyFilledWith = FillEmptyWith.None
    )
  )
)
