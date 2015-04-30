// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Applegate extends GameRules(
  id = "applegate",
  title = "Applegate",
  description = "This game has similarities to both ^spider^ and ^yukon^, and may be an older version of ^scorpion^",
  waste = Some(
    WasteRules(
      name = "Reserve"
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      moveCompleteSequencesOnly = true,
      canMoveFrom = FoundationCanMoveFrom.Never
    )
  ),
  tableaus = Seq(
    TableauRules(
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "UUUUUUU",
        "DDUUUUU",
        "DDUUUUU",
        "DDUUUUU",
        "DDUUUUU",
        "DDUUUUU",
        "DDUUUUU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      wrapFromKingToAce = true,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  complete = false
)

