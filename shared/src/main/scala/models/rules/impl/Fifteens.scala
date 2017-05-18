package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Foundation low rank (F0b): 20 (Any Card)
 *   Maximum cards for foundation (F0m): 0
 *   Auto-fill an empty tableau from (T0af): 4 (Stock)
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Empty tableau is filled with (T0f): 5 (No card)
 *   Tableau piles (T0n): 16
 *   Tableau suit match rule for building (T0s): 0 (May not build)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 8 (Never)
 *   Left mouse interface function (leftfunc): 1
 *   Similar to (like): straightfifteens
 *   Card removal method (pairs): 24 (Remove sets adding to 15 or four 10s, Js, Qs, or Ks)
 */
object Fifteens extends GameRules(
  id = "fifteens",
  completed = true,
  title = "Fifteens",
  like = Some("straightfifteens"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/fifteens.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/fifteens.html"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/fifteens.htm"),
    Link("Solitaire Central", "www.solitairecentral.com/rules/Fifteens.html"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Fifteens.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/fifteens.php"),
    Link("Solitaire Game Rules.com", "solitaire-game-rules.com/games/fifteens.htm")
  ),
  layout = ":::::s:f|t",
  cardRemovalMethod = CardRemovalMethod.RemoveSetsAddingToFifteenOrFour10JQK,
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
