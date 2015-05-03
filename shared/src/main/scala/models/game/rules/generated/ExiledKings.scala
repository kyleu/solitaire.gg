// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object ExiledKings extends GameRules(
  id = "exiledkings",
  title = "Exiled Kings",
  like = Some("citadel"),
  description = "A more difficult variation of ^citadel^ where spaces can only be filled by kings.",
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
      numPiles = 8,
      initialCards = InitialCards.Count(6),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Kings,
      actionDuringDeal = PileAction.MoveToFoundation
    )
  ),
  complete = false
)

