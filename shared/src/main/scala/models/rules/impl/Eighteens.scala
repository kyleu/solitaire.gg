package models.rules.impl

import models.rules._

object Eighteens extends GameRules(
  id = "eighteens",
  completed = true,
  title = "Eighteens",
  like = Some("simplepairs"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/eighteens.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/eighteens.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Eighteens.htm")
  ),
  layout = "s:::f|t",
  cardRemovalMethod = CardRemovalMethod.RemoveSetsOfOneFaceCardAnd3ThatAddToEighteen,
  stock = Some(StockRules(dealTo = StockDealTo.Never, maximumDeals = Some(1))),
  foundations = Seq(FoundationRules(numPiles = 4, lowRank = FoundationLowRank.AnyCard, maxCards = 0, autoMoveCards = true)),
  tableaus = Seq(TableauRules(
    numPiles = 12,
    initialCards = InitialCards.Count(1),
    cardsFaceDown = TableauFaceDownCards.Count(0),
    suitMatchRuleForBuilding = SuitMatchRule.None,
    suitMatchRuleForMovingStacks = SuitMatchRule.None,
    autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock,
    emptyFilledWith = FillEmptyWith.None
  ))
)
