// Generated 2015-04-26 for Scalataire.
package models.game.generated

import models.game._
import models.game.rules._

object ForeCell extends GameRules(
  id = "forecell",
  title = "ForeCell",
  description = "A Swedish predecessor to ^freecell^, originally one of many games called \"Napolean at St. Helena\". The initial layout is a bit d" +
  "ifferent from FreeCell, and spaces can only be filled by Kings.",
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
      initialCards = InitialCards.Count(6),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  cells = Some(
    CellRules(

      initialCards = 4
    )
  ),
  complete = false
)

