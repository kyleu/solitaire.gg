// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
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
 *   Card removal method (pairs): 8 (Remove pairs adding to 11 or J-Q-K)
 *   Related games (related): suitelevens
 */
object Elevens extends GameRules(
  id = "elevens",
  title = "Elevens",
  like = Some("tens"),
  related = Seq("suitelevens"),
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Elevens"),
    Link("Solitaire Central", "www.solitairecentral.com/rules/Elevens.html"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/elevens.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Elevens.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/elevens.htm"),
    Link("Jan Wolter's Experiments", "/article/simplepairs.html")
  ),
  description = "A set removal sets of cards adding to 15 or sets containing ten through king.",
  cardRemovalMethod = CardRemovalMethod.RemovePairsAddingToElevenOrJQK,
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
