// Generated 2015-04-26 for Scalataire.
package models.game.generated

import models.game._
import models.game.rules._

object Accordion extends GameRules(
  id = "accordion",
  title = "Accordion",
  description = "This deck compression game was once known as \"Idle Year\" because it was believed that you could pl" +
  "ay for a year without winning, but players have now discovered strategies that make it possible to w" +
  "in almost every game.",
  cardRemovalMethod = CardRemovalMethod.StackSameRankOrSuitInWaste,
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      canMoveFrom = FoundationCanMoveFrom.Never,
      offscreen = true,
      autoMoveCards = true
    )
  ),
  complete = false
)

