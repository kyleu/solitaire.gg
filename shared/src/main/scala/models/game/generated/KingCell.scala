// Generated rules for Scalataire.
package models.game.generated

import models.game._
import models.game.rules._

object KingCell extends GameRules(
  id = "kingcell",
  title = "KingCell",
  description = "A variation of ^freecell^ where we build down regardless of suit instead of by alternate color, and only kings may be played to em" +
  "pty tableau spaces",
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
      numPiles = 8,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  cells = Some(CellRules()),
  complete = false
)

