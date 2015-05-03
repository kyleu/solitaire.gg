// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Penguin extends GameRules(
  id = "penguin",
  title = "Penguin",
  related = Seq("opus"),
  description = "A satisfying game with seven cells developed by David Parlett where one of the cards you need to start the foundation is always bu" +
  "ried at the bottom of the first tableau pile.",
  deckOptions = DeckOptions(
    lowRank = Rank.Unknown
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      initialCards = 3,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      initialCards = InitialCards.Count(7),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      emptyFilledWith = TableauFillEmptyWith.Kings,
      pilesWithLowCardsAtBottom = 1
    )
  ),
  cells = Some(
    CellRules(
      numPiles = 7
    )
  ),
  complete = false
)

