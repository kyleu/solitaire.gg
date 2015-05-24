// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Tableau initial cards (T0d): -1 (1 to n cards)
 *   Tableau cards face down (T0df): 100
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 12
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Similar to (like): harp
 *   Maximum deals from stock (maxdeals): 4 (4)
 *   Number of decks (ndecks): 3 (3 decks)
 */
object Tripleharp extends GameRules(
  id = "tripleharp",
  title = "Tripleharp",
  like = Some("harp"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/triple_harp.htm")),
  description = "A three-deck version of ^harp^ invented by Thomas Warfield.",
  deckOptions = DeckOptions(
    numDecks = 3
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(4)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 12,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 12,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
