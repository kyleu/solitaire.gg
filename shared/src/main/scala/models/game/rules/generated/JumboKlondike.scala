// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Tableau initial cards (T0d): -1 (1 to n cards)
 *   Tableau cards face down (T0df): 100
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 7
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau suit match rule for moving stacks (T0ts): 4 (In alternating colors)
 *   Deal cards from stock (dealchunk): 3 (Three at a time)
 *   Similar to (like): klondike
 *   Maximum deals from stock (maxdeals): 0
 *   Custom suits (suits): 399
 */
object JumboKlondike extends GameRules(
  id = "jumboklondike",
  title = "Jumbo Klondike",
  like = Some("klondike"),
  description = "^klondike^ played with a six-suit deck.",
  deckOptions = DeckOptions(
    suits = Seq(Suit.Hearts, Suit.Spades, Suit.Diamonds, Suit.Clubs, Suit.Stars, Suit.Tridents)
  ),
  stock = Some(
    StockRules(
      cardsDealt = StockCardsDealt.Count(3)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
