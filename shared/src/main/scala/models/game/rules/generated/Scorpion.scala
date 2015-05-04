// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 0 (Never)
 *   Foundation add complete sequences only (F0cs): true
 *   Stock name (S0Nm): Reserve
 *   Tableau initial cards (T0d): -2 (custom)
 *   Custom initial cards (T0ds): DDDUUUU DDDUUUU DDDUUUU DDDUUUU UUUUUUU UUUUUUU UUUUUUU
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 7
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Tableau rank match rule for moving stacks (T0tr): 0x1fff
 *   Tableau suit match rule for moving stacks (T0ts): 5 (Regardless of suit)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 2 (To all tableau piles)
 *   Related games (related): chelicera, chinese
 */
object Scorpion extends GameRules(
  id = "scorpion",
  title = "Scorpion",
  related = Seq("chelicera", "chinese"),
  description = "A game with a seven-by-seven tableau, where three cards in the first four piles start face down. Unsorted stacks of cards can be m" +
  "oved around, as in ^yukon^, but cards cannot be moved to the foundation until they form complete sequences, as in ^spider^.",
  stock = Some(
    StockRules(
      name = "Reserve",
      dealTo = StockDealTo.Tableau,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      moveCompleteSequencesOnly = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "DDDUUUU",
        "DDDUUUU",
        "DDDUUUU",
        "DDDUUUU",
        "UUUUUUU",
        "UUUUUUU",
        "UUUUUUU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  complete = false
)

