// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object EphemeralFreeCell extends GameRules(
  id = "ephemeralfreecell",
  title = "Ephemeral FreeCell",
  description = "Ephemeral FreeCell is like standard ^freecell^, except that one of the cells will vanish after it's first use. Michael Keller inve" +
  "nted this idea. You can experiment with different numbers of ephemeral cells.",
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
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  cells = Some(
    CellRules(
      numEphemeral = 1
    )
  ),
  complete = false
)

