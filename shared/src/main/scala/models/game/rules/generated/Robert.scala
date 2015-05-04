// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Foundation low rank (F0b): 20 (Any Card)
 *   Foundation initial cards (F0d): 1 (1 cards)
 *   Maximum cards for foundation (F0m): 0
 *   Number of foundation piles (F0n): 1 (1 stack)
 *   Foundation rank match rule (F0r): 0x0020|0x0080
 *   Foundation suit match rule (F0s): 5 (Regardless of suit)
 *   Tableau sets (Tn): 0 (0 tableau sets)
 *   Maximum deals from stock (maxdeals): 3 (3)
 *   Related games (related): bobby
 */
object Robert extends GameRules(
  id = "robert",
  title = "Robert",
  related = Seq("bobby"),
  description = "An nearly unwinnable game with no tableau.",
  stock = Some(
    StockRules(
      maximumDeals = Some(3)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      lowRank = FoundationLowRank.AnyCard,
      initialCards = 1,
      suitMatchRule = SuitMatchRule.Any,
      rankMatchRule = RankMatchRule.UpOrDown,
      wrapFromKingToAce = true,
      maxCards = 0,
      autoMoveCards = true
    )
  ),
  complete = false
)

