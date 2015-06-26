// Generated rules for Solitaire.gg.
package models.game.rules.impl

import models.game.rules._

/**
 * Original Settings:
 *   Auto-fill an empty tableau from (T0af): 4 (Stock)
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Empty tableau is filled with (T0f): 5 (No card)
 *   Tableau piles (T0n): 9
 *   Tableau suit match rule for building (T0s): 0 (May not build)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 8 (Never)
 *   Left mouse interface function (leftfunc): 1
 *   Similar to (like): tens
 *   Card removal method (pairs): 3 (Remove nines, pairs adding to 9, or 10-J-Q-K)
 */
object Nines extends GameRules(
  id = "nines",
  title = "Nines",
  like = Some("tens"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/nines.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/nines.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Nines.htm"),
    Link("Jan Wolter's Experiments", "/article/simplepairs.html")
  ),
  description = "A variation on ^simplepairs^ pairs that add to 9 or set of ten through king. Much luck required.",
  cardRemovalMethod = CardRemovalMethod.RemoveNinesOrPairsAddingToNineOr10JQK,
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Never,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 9,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock,
      emptyFilledWith = FillEmptyWith.None
    )
  )
)
