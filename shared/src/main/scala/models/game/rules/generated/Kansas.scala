// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Kansas extends GameRules(
  id = "kansas",
  title = "Kansas",
  description = "Like ^rainbow^ this is a ^canfield^ variation where you build regardless of suit, but this is a bit more difficult because you onl" +
  "y have three tableau piles.",
  deckOptions = DeckOptions(
    lowRank = Some(Rank.Unknown)
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      initialCards = 1,
      wrapFromKingToAce = true,
      canMoveFrom = FoundationCanMoveFrom.Never,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 3,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Reserve,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  reserves = Some(
    ReserveRules(
      name = "Reserve",
      numPiles = 1,
      initialCards = 13,
      cardsFaceDown = 100
    )
  ),
  complete = false
)

