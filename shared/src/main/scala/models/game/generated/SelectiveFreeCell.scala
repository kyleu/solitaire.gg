// Generated 2015-04-26 for Scalataire.
package models.game.generated

import models.game._
import models.game.rules._

object SelectiveFreeCell extends GameRules(
  id = "selectivefreecell",
  title = "Selective FreeCell",
  description = "A variation of ^freecell^ where the first card played to the foudnation sets the base value for all the foundations.",
  deckOptions = DeckOptions(
    lowRank = Some(Rank.Unknown)
  ),
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
  cells = Some(CellRules()),
  complete = false
)

