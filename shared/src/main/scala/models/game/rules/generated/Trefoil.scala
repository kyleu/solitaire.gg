// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Trefoil extends GameRules(
  id = "trefoil",
  title = "Trefoil",
  like = Some("labellelucie"),
  description = "A slightly easier variation of ^labellelucie^ where the aces start on the foundation and there are fewer tableau columns.",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      initialCards = 4,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 16,
      initialCards = InitialCards.Count(3),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  special = Some(
    SpecialRules(
      redealsAllowed = 2,
      dealOrder = DealOrder.ColumnsLeftToRightTopToBottom
    )
  ),
  complete = false
)

