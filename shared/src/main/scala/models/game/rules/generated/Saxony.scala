// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Saxony extends GameRules(
  id = "saxony",
  title = "Saxony",
  description = "You have four cells, four reserve piles where you can build down in suit, and eight tableau piles, where cards are dealt, but no b" +
  "uilding is allowed.",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Reserve,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      initialCards = InitialCards.PileIndex,
      wrapFromKingToAce = true,
      canMoveFrom = FoundationCanMoveFrom.Never
    )
  ),
  tableaus = Seq(
    TableauRules(
      name = "Reserve",
      numPiles = 4,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  cells = Some(
    CellRules(
      initialCards = 4
    )
  ),
  reserves = Some(
    ReserveRules(
      name = "Tableau",
      numPiles = 8,
      initialCards = 1,
      cardsFaceDown = 100
    )
  ),
  complete = false
)

