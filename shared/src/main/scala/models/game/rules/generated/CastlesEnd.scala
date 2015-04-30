// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object CastlesEnd extends GameRules(
  id = "castlesend",
  title = "Castle's End",
  description = "A slightly different version of ^chessboard^ with a two-card reserve.",
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
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  reserves = Some(
    ReserveRules(
      name = "Reserve",
      numPiles = 2,
      initialCards = 1,
      cardsFaceDown = 100
    )
  ),
  complete = false
)

