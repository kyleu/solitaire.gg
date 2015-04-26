// Generated 2015-04-26 for Scalataire.
package models.game.generated

import models.game._
import models.game.rules._

// scalastyle:off
object DemonFan extends GameRules(
  id = "demonfan",
  title = "Demon Fan",
  description = "A very easy game where you build down in alternate colors, and are allowed six redeals.",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      canMoveFrom = FoundationCanMoveFrom.Never,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 18,
      initialCards = InitialCards.RestOfDeck,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  complete = false
)
// scalastyle:on

