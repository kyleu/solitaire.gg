// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Deuces extends GameRules(
  id = "deuces",
  title = "Deuces",
  description = "A more difficult variation of ^busyaces^ with fewer tableau piles.",
  deckOptions = DeckOptions(
    numDecks = 2,
    lowRank = Some(Rank.Two)
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      initialCards = 8,
      wrapFromKingToAce = true,
      canMoveFrom = FoundationCanMoveFrom.Never,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)

