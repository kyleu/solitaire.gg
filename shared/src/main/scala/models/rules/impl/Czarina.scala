// Generated rules for Solitaire.gg.
package models.rules.impl

import models.card.Rank
import models.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Foundation initial cards (F0d): 1 (1 cards)
 *   Auto-fill an empty tableau from (T0af): 4 (Stock)
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Tableau piles (T0n): 5
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Similar to (like): fourseasons
 *   Low card (lowpip): -2 (?)
 *   Related games (related): corners
 *   Enable super moves, whatever those are (supermoves): 1
 */
object Czarina extends GameRules(
  id = "czarina",
  completed = false,
  title = "Czarina",
  like = Some("fourseasons"),
  related = Seq("corners"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/czarina.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/czarina.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/czarina.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Czarina.htm")
  ),
  description = "A variation on ^fourseasons^ where spaces are filled automatically from the stock.",
  layout = Some("swf|t"),
  deckOptions = DeckOptions(
    lowRank = Rank.Unknown
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      initialCards = 1,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 5,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock
    )
  )
)