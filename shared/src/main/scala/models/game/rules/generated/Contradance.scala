// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Foundation low rank (F0b): 5 (5)
 *   Maximum cards for foundation (F0m): 6
 *   Foundation rank match rule (F0r): 0x0020
 *   Foundation low rank (F1b): 6 (6)
 *   Maximum cards for foundation (F1m): 7
 *   Foundation Sets (Fn): 2
 *   Tableau sets (Tn): 0 (0 tableau sets)
 *   Maximum deals from stock (maxdeals): 2 (2)
 *   Number of decks (ndecks): 2 (2 decks)
 */
object Contradance extends GameRules(
  id = "contradance",
  title = "Contradance",
  description = "A variation of ^sixesandsevens^ that is just as brainless as ^captivequeens^, but requires vastly more luck to ever win.",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(2)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      lowRank = FoundationLowRank.SpecificRank(Rank.King),
      rankMatchRule = RankMatchRule.Down,
      wrapFromKingToAce = true,
      maxCards = 6,
      autoMoveCards = true
    ),
    FoundationRules(
      setNumber = 1,
      numPiles = 8,
      lowRank = FoundationLowRank.SpecificRank(Rank.King),
      wrapFromKingToAce = true,
      maxCards = 7,
      autoMoveCards = true
    )
  ),
  complete = false
)

