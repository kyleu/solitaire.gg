// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau name (T0Nm): Flower Beds
 *   Tableau initial cards (T0d): 6 (6 cards)
 *   Tableau piles (T0n): 6
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Tableau suit match rule for moving stacks (T0ts): 1 (In same suit)
 *   Waste name (W0Nm): Bouquet
 *   Playable waste cards (W0a): true
 *   Number of cards shown (W0cardsShown): 20
 *   *W0s (W0s): true
 *   Similar to (like): flowergarden
 *   Related games (related): wildflower, brigade
 */
object Wildflower extends GameRules(
  id = "wildflower",
  title = "Wildflower",
  like = Some("flowergarden"),
  related = Seq("wildflower", "brigade"),
  description = "A variation of ^flowergarden^ in which you may move sequences of cards of the same suit together.",
  waste = Some(
    WasteRules(
      name = "Bouquet",
      cardsShown = 20
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      name = "Flower Beds",
      numPiles = 6,
      initialCards = InitialCards.Count(6),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)

