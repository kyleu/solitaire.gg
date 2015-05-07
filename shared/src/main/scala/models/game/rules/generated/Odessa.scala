// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): -2 (custom)
 *   Custom initial cards (T0ds): DDDUUU DDDUUUUU DDDUUUUU DDDUUUUU DDDUUUUU DDDUUUUU DDDUUU
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 7
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Tableau rank match rule for moving stacks (T0tr): 0x1fff
 *   Tableau suit match rule for moving stacks (T0ts): 5 (Regardless of suit)
 *   Number of waste piles (W0n): 0
 *   Similar to (like): russian
 *   Related games (related): doublerussian, triplerussian, ukrainian, russiancell, odessa, tenacross
 */
object Odessa extends GameRules(
  id = "odessa",
  title = "Odessa",
  like = Some("russian"),
  related = Seq("doublerussian", "triplerussian", "ukrainian", "russiancell", "odessa", "tenacross"),
  links = Seq(Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Odessa.html.en")),
  description = "A variant of ^russian^ with a different starting tableau.",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "DDDUUU",
        "DDDUUUUU",
        "DDDUUUUU",
        "DDDUUUUU",
        "DDDUUUUU",
        "DDDUUUUU",
        "DDDUUU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      emptyFilledWith = FillEmptyWith.Kings
    )
  ),
  complete = false
)

