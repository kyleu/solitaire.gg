// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object HalfCell extends GameRules(
  id = "halfcell",
  title = "HalfCell",
  description = "^freecell^ with only two foundation piles.",
  foundations = Seq(
    FoundationRules(
      numPiles = 2,
      suitMatchRule = SuitMatchRule.AlternatingColors,
      wrapFromKingToAce = true,
      maxCards = 26,
      canMoveFrom = FoundationCanMoveFrom.Never,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  cells = Some(CellRules()),
  complete = false
)

