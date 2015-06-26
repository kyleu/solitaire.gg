// Generated rules for Solitaire.gg.
package models.game.rules.impl

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 0 (Never)
 *   Foundation initial cards (F0d): 1 (1 cards)
 *   Auto-fill an empty tableau from (T0af): 4 (Stock)
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Tableau piles (T0n): 5
 *   Tableau suit match rule for building (T0s): 0 (May not build)
 *   Left mouse interface function (leftfunc): 2
 *   Similar to (like): czarina
 *   Low card (lowpip): -2 (?)
 *   Maximum deals from stock (maxdeals): 3 (3)
 *   Enable super moves, whatever those are (supermoves): 1
 */
object Corners extends GameRules(
  id = "corners",
  title = "Corners",
  like = Some("czarina"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/corners.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/corners.htm")
  ),
  description = "This completely mindless variation on ^czarina^ allows no building on the tableau, but allows three passes through the stock.",
  deckOptions = DeckOptions(
    lowRank = Rank.Unknown
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(3)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      initialCards = 1
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 5,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock
    )
  )
)
