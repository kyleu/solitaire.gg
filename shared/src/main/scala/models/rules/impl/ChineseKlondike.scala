// Generated rules for Solitaire.gg.
package models.rules.impl

import models.card.Suit
import models.rules._

/**
 * Original Settings:
 *   Tableau initial cards (T0d): -1 (1 to n cards)
 *   Tableau cards face down (T0df): 100
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 12
 *   Tableau suit match rule for building (T0s): 2 (In different suits)
 *   Tableau suit match rule for moving stacks (T0ts): 4 (In alternating colors)
 *   Deal cards from stock (dealchunk): 3 (Three at a time)
 *   Similar to (like): klondike
 *   Maximum deals from stock (maxdeals): 0
 *   Number of decks (ndecks): 4 (4 decks)
 *   Custom suits (suits): 35
 */
object ChineseKlondike extends GameRules(
  id = "chineseklondike",
  title = "Chinese Klondike",
  like = Some("klondike"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/chinese_klondike.htm")),
  description = "A three-suit version of ^klondike^.",
  deckOptions = DeckOptions(
    numDecks = 4,
    suits = Seq(Suit.Hearts, Suit.Spades, Suit.Diamonds)
  ),
  stock = Some(
    StockRules(
      cardsDealt = StockCardsDealt.Count(3)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 16,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 12,
      suitMatchRuleForBuilding = SuitMatchRule.DifferentSuits,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
