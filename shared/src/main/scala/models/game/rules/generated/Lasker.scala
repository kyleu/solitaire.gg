// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Lasker extends GameRules(
  id = "lasker",
  title = "Lasker",
  description = "A version of ^chessboard^ where sequences may be moved.",
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
      numPiles = 10,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      rankMatchRuleForBuilding = RankMatchRule.UpOrDown,
      wrapFromKingToAce = true,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      rankMatchRuleForMovingStacks = RankMatchRule.UpOrDown,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)

