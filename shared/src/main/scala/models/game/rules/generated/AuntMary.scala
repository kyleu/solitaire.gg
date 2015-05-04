// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Tableau initial cards (T0d): -1 (1 to n cards)
 *   Tableau cards face down (T0df): 0
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 6
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau suit match rule for moving stacks (T0ts): 4 (In alternating colors)
 *   Deal cards from stock (dealchunk): 1 (One by one)
 *   Similar to (like): thoughtful
 *   Maximum deals from stock (maxdeals): 1 (1)
 *   Related games (related): whitehorse, kingsley, trigon, goldmine, thoughtful, klondikegallery, chineseklon...
 */
object AuntMary extends GameRules(
  id = "auntmary",
  title = "Aunt Mary",
  like = Some("thoughtful"),
  related = Seq(
    "whitehorse", "kingsley", "trigon", "goldmine", "thoughtful", "klondikegallery", "chineseklondike", "athena",
    "saratoga", "endlessharp", "smokey", "spike", "gilbert", "jumboklondike", "chinaman"
  ),
  description = "A difficult ^klondike^ variation where the tableau contains one fewer pile but all cards are face up.",
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
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
      numPiles = 6,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  complete = false
)

