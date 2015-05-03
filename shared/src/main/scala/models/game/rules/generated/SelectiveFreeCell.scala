// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object SelectiveFreeCell extends GameRules(
  id = "selectivefreecell",
  title = "Selective FreeCell",
  like = Some("freecell"),
  description = "A variation of ^freecell^ where the first card played to the foudnation sets the base value for all the foundations.",
  deckOptions = DeckOptions(
    lowRank = Rank.Unknown
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
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

