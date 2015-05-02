// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

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

