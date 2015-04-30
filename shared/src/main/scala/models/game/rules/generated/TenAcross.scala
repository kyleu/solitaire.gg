// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object TenAcross extends GameRules(
  id = "tenacross",
  title = "Ten Across",
  description = "A variation of ^russian^ with a different starting tableau and two cells, which start full.",
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
      numPiles = 10,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "UUUUU",
        "DUUUU",
        "DDUUU",
        "DDDUU",
        "DDDDU",
        "DDDDU",
        "DDDUU",
        "DDUUU",
        "DUUUU",
        "UUUUU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  cells = Some(
    CellRules(

      numPiles = 2,
      initialCards = 2
    )
  ),
  complete = false
)

