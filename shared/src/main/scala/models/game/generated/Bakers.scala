// Generated rules for Scalataire.
package models.game.generated

import models.game._
import models.game.rules._

object Bakers extends GameRules(
  id = "bakers",
  title = "Baker's",
  description = "A predecessor of ^freecell^ invented by C. L. Baker. The rules are the same as FreeCell, except that you build down in suit instea" +
  "d of in alternating colors.",
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
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  cells = Some(CellRules()),
  complete = false
)

