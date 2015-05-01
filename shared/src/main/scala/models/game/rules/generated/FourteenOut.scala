// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object FourteenOut extends GameRules(
  id = "fourteenout",
  title = "Fourteen Out",
  description = "An interesting game in which you remove pairs that add the fourteen.",
  cardRemovalMethod = CardRemovalMethod.RemovePairsAddingToFourteen,
  foundations = Seq(
    FoundationRules(
      wrapFromKingToAce = true,
      canMoveFrom = FoundationCanMoveFrom.Never,
      visible = false,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 12,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  complete = false
)

