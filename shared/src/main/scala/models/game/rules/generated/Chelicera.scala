// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 0 (Never)
 *   Foundation add complete sequences only (F0cs): true
 *   Foundation Sets (Fn): 0
 *   Stock name (S0Nm): Stock
 *   Auto-fill an empty tableau from (T0af): 4 (Stock)
 *   *T0an (T0an): 3
 *   Tableau initial cards (T0d): -2 (custom)
 *   Custom initial cards (T0ds): UUUU UUUU UUUU UUUU UUUUUUU UUUUUUU UUUUUUU
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 7
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Tableau rank match rule for moving stacks (T0tr): 0x1fff
 *   Tableau suit match rule for moving stacks (T0ts): 5 (Regardless of suit)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 8 (Never)
 *   Similar to (like): scorpion
 *   Related games (related): chelicera, chinese
 *   Victory condition (victory): 3 (All cards on tableau sorted)
 */
object Chelicera extends GameRules(
  id = "chelicera",
  title = "Chelicera",
  like = Some("scorpion"),
  description = "A variation on ^scorpion^ invented by Erik den Hollander in which we fill spaces with three cards from the stock instead of dealin" +
  "g from the stock.",
  victoryCondition = VictoryCondition.AllOnTableauSorted,
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Never,
      maximumDeals = Some(1)
    )
  ),
  tableaus = Seq(
    TableauRules(
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "UUUU",
        "UUUU",
        "UUUU",
        "UUUU",
        "UUUUUUU",
        "UUUUUUU",
        "UUUUUUU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock,
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  complete = false
)

