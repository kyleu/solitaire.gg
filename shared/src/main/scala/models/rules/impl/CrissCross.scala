package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Foundation low rank (F0b): 20 (Any Card)
 *   Keep foundation off-screen (F0i): true
 *   Maximum cards for foundation (F0m): 0
 *   Auto-fill an empty tableau from (T0af): 4 (Stock)
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Empty tableau is filled with (T0f): 5 (No card)
 *   Tableau piles (T0n): 4
 *   Tableau suit match rule for building (T0s): 0 (May not build)
 *   Number of waste piles (W0n): 1
 *   Deal cards from stock (dealto): 9
 *   Left mouse interface function (leftfunc): 1
 *   Similar to (like): simplepairs
 *   Card removal method (pairs): 1 (Remove pairs of same rank)
 */
object CrissCross extends GameRules(
  id = "crisscross",
  completed = false,
  title = "Criss Cross",
  like = Some("simplepairs"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/criss_cross.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/CrissCross.htm")
  ),
  description = "A variation of ^simplepairs^ that requires a very large dose of pure luck to win.",
  layout = "swf|t",
  cardRemovalMethod = CardRemovalMethod.RemovePairsOfSameRank,
  stock = Some(
    StockRules(
      dealTo = StockDealTo.WasteOrPairManually,
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      lowRank = FoundationLowRank.AnyCard,
      maxCards = 0,
      canMoveFrom = FoundationCanMoveFrom.Never,
      visible = false,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
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
