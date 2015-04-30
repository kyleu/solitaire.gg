// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Fly extends GameRules(
  id = "fly",
  title = "Fly",
  description = "A variation of ^frog^ where the aces start on the foundation.",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Manually,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      initialCards = InitialCards.PileIndex,
      suitMatchRule = SuitMatchRule.Any,
      wrapFromKingToAce = true,
      canMoveFrom = FoundationCanMoveFrom.Never,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 5,
      initialCards = InitialCards.Count(0),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      rankMatchRuleForBuilding = RankMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Aces,
      mayMoveToNonEmptyFrom = Seq("Stock"),
      mayMoveToEmptyFrom = Seq("Stock")
    )
  ),
  reserves = Some(
    ReserveRules(
      name = "Fly",
      numPiles = 1,
      initialCards = 13,
      cardsFaceDown = 0
    )
  ),
  complete = false
)

