// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object SpiderCells extends GameRules(
  id = "spidercells",
  title = "SpiderCells",
  description = "A ^freecell^ variant where you need to build complete alternating color sequences on the tableau.",
  victoryCondition = VictoryCondition.AllOnTableauSorted,
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  cells = Some(CellRules()),
  complete = false
)

