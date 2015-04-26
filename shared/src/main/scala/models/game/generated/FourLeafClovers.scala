// Generated 2015-04-26 for Scalataire.
package models.game.generated

import models.game._
import models.game.rules._

object FourLeafClovers extends GameRules(
  id = "fourleafclovers",
  title = "Four Leaf Clovers",
  description = "A single foundation pile is built regardless of suit from ace to king and then from ace to king agai" +
  "n with the help of a tableau where you can build both up and down.",
  foundations = Seq(
    FoundationRules(
      suitMatchRule = SuitMatchRule.Any,
      wrapFromKingToAce = true,
      maxCards = 0,
      canMoveFrom = FoundationCanMoveFrom.Never,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 13,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      rankMatchRuleForBuilding = RankMatchRule.UpOrDown,
      wrapFromKingToAce = true,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  complete = false
)

