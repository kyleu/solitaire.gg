// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Foundation low rank (F0b): 20 (Any Card)
 *   Maximum cards for foundation (F0m): 0
 *   Auto-fill an empty tableau from (T0af): 4 (Stock)
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Empty tableau is filled with (T0f): 5 (No card)
 *   Tableau piles (T0n): 12
 *   Tableau suit match rule for building (T0s): 0 (May not build)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 8 (Never)
 *   Left mouse interface function (leftfunc): 1
 *   Similar to (like): simplepairs
 *   Card removal method (pairs): 15 (Remove sets of 1 face card and 3 that add to 18)
 */
object Eighteens extends GameRules(
  id = "eighteens",
  title = "Eighteens",
  like = Some("simplepairs"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/eighteens.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/eighteens.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Eighteens.htm")
  ),
  description = "A game where you remove singleton aces, or sets consisting of a face card with three other cards that add to eighteen.",
  cardRemovalMethod = CardRemovalMethod.RemoveSetsOfOneFaceCardAnd3ThatAddToEighteen,
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
      wrapFromKingToAce = true,
      maxCards = 0,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 12,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock
    )
  )
)
