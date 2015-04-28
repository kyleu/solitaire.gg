// Generated rules for Scalataire.
package models.game.generated

import models.game._
import models.game.rules._

object SuperChallengeFreeCell extends GameRules(
  id = "superchallengefreecell",
  title = "Super Challenge FreeCell",
  description = "A version of ^freecell^ invented by Thomas Warfield where the aces and twos are always at the bottoms of the eight stacks and wher" +
  "e spaces can only be filled by Kings.",
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
      emptyFilledWith = TableauFillEmptyWith.Kings,
      pilesWithLowCardsAtBottom = 8
    )
  ),
  cells = Some(CellRules()),
  complete = false
)

