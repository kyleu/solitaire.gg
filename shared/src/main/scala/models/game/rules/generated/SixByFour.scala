// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object SixByFour extends GameRules(
  id = "sixbyfour",
  title = "Six by Four",
  description = "A much harder ^freecell^ variant with two fewer tableau columns.",
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
      numPiles = 6,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  cells = Some(CellRules()),
  complete = false
)

