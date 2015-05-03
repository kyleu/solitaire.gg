// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object FourByTen extends GameRules(
  id = "fourbyten",
  title = "Four by Ten",
  description = "A ^freecell^ variation with lots of cells and not so many tableau piles.",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 4,
      initialCards = InitialCards.Count(13),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  cells = Some(
    CellRules(
      numPiles = 10
    )
  ),
  complete = false
)

