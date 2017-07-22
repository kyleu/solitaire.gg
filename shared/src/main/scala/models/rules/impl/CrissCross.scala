package models.rules.impl

import models.rules._

object CrissCross extends GameRules(
  id = "crisscross",
  completed = false,
  title = "Criss Cross",
  like = Some("simplepairs"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/criss_cross.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/CrissCross.htm")
  ),
  layout = "swf|t",
  cardRemovalMethod = CardRemovalMethod.RemovePairsOfSameRank,
  stock = Some(StockRules(dealTo = StockDealTo.WasteOrPairManually, maximumDeals = Some(1))),
  waste = Some(WasteRules()),
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 4,
      lowRank = FoundationLowRank.AnyCard,
      maxCards = 0,
      canMoveFrom = FoundationCanMoveFrom.Never,
      visible = false,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 4,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock,
      emptyFilledWith = FillEmptyWith.None
    )
  )
)
