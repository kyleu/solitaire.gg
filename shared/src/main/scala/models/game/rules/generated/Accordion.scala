// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Keep foundation off-screen (F0i): true
 *   Foundation Sets (Fn): 1
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau sets (Tn): 0 (0 tableau sets)
 *   Playable waste cards (W0a): true
 *   Number of cards shown (W0cardsShown): 52
 *   Left mouse interface function (leftfunc): 0x4
 *   Card removal method (pairs): 20 (Stack cards of same rank/suit in waste)
 *   Touch interface function (touchfunc): 0x4
 */
object Accordion extends GameRules(
  id = "accordion",
  title = "Accordion",
  description = "This deck compression game was once known as \"Idle Year\" because it was believed that you could play for a year without winning," +
  " but players have now discovered strategies that make it possible to win almost every game.",
  cardRemovalMethod = CardRemovalMethod.StackSameRankOrSuitInWaste,
  waste = Some(
    WasteRules(
      cardsShown = 52
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      canMoveFrom = FoundationCanMoveFrom.Never,
      visible = false,
      autoMoveCards = true
    )
  ),
  complete = false
)

