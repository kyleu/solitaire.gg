// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object TripleFreeCell extends GameRules(
  id = "triplefreecell",
  title = "Triple FreeCell",
  description = "Thomas Warfield's three-deck version of ^freecell^.",
  deckOptions = DeckOptions(
    numDecks = 3
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 12,
      wrapFromKingToAce = true,
      canMoveFrom = FoundationCanMoveFrom.Never,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 13,
      initialCards = InitialCards.Count(12),
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

