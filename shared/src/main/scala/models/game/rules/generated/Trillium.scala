// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Trillium extends GameRules(
  id = "trillium",
  title = "Trillium",
  description = "A ^spider^ variant with a 13x4 tableau where you build down by alternate color.",
  victoryCondition = VictoryCondition.AllOnTableauSorted,
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Tableau,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      suitMatchRule = SuitMatchRule.AlternatingColors,
      wrapFromKingToAce = true,
      moveCompleteSequencesOnly = true,
      canMoveFrom = FoundationCanMoveFrom.Never
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 13,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.OddNumbered,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)

