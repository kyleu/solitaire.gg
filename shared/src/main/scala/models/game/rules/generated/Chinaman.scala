// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Tableau initial cards (T0d): -1 (1 to n cards)
 *   Tableau cards face down (T0df): 100
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 7
 *   Tableau suit match rule for building (T0s): 2 (In different suits)
 *   Tableau suit match rule for moving stacks (T0ts): 2 (In different suits)
 *   Deal cards from stock (dealchunk): 3 (Three at a time)
 *   Similar to (like): klondike
 *   Maximum deals from stock (maxdeals): 2 (2)
 *   Related games (related): whitehorse, kingsley, trigon, goldmine, thoughtful, klondikegallery, chineseklon...
 */
object Chinaman extends GameRules(
  id = "chinaman",
  title = "Chinaman",
  like = Some("klondike"),
  related = Seq(
    "whitehorse", "kingsley", "trigon", "goldmine", "thoughtful", "klondikegallery", "chineseklondike", "athena",
    "saratoga", "endlessharp", "smokey", "spike", "gilbert", "jumboklondike", "chinaman"
  ),
  description = "A ^klondike^ variant where we build by different suits.",
  stock = Some(
    StockRules(
      maximumDeals = Some(2),
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
      suitMatchRuleForBuilding = SuitMatchRule.DifferentSuits,
      suitMatchRuleForMovingStacks = SuitMatchRule.DifferentSuits,
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  complete = false
)

