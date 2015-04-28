// Generated rules for Scalataire.
package models.game.generated

import models.game._
import models.game.rules._

object SeaTowers extends GameRules(
  id = "seatowers",
  title = "Sea Towers",
  description = "A popular ^freecell^ variation invented in 1988 by Art Cabral. The initial layout is different, and we must build down in suit ins" +
  "tead of in alternating colors.",
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
      initialCards = InitialCards.Count(5),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  cells = Some(
    CellRules(

      initialCards = 2
    )
  ),
  complete = false
)

