// Generated 2015-04-26 for Scalataire.
package models.game.generated

import models.game._
import models.game.rules._

object ChallengeFreeCell extends GameRules(
  id = "challengefreecell",
  title = "Challenge FreeCell",
  description = "A version of ^freecell^ invented by Thomas Warfield where the aces and twos are always at the bottom" +
  "s of the eight stacks.",
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
      emptyFilledWith = TableauFillEmptyWith.Aces,
      pilesWithLowCardsAtBottom = 8
    )
  ),
  cells = Some(CellRules()),
  complete = false
)

