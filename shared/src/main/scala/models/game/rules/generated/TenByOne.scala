// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object TenByOne extends GameRules(
  id = "tenbyone",
  title = "Ten By One",
  description = "Ten tableau piles and one cell make a game with similarities to ^freecell^ and ^vineyard^.",
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
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  cells = Some(
    CellRules(

      numPiles = 1
    )
  ),
  complete = false
)

