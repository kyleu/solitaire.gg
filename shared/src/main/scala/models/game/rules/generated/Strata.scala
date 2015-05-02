// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Strata extends GameRules(
  id = "strata",
  title = "Strata",
  description = "An eight-by-eight square tableau, a short deck, and two redeals make this game interesting.",
  deckOptions = DeckOptions(
    numDecks = 2,
    ranks = Seq(Rank.Seven, Rank.Eight, Rank.Nine, Rank.Ten, Rank.Jack, Rank.Queen, Rank.King, Rank.Ace)
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      wrapFromKingToAce = true,
      canMoveFrom = FoundationCanMoveFrom.Never,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(8),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  special = Some(
    SpecialRules(
      redealsAllowed = 2,
      pickupOrder = DealOrder.ColumnsRightToLeftBottomToTop,
      shuffleBeforeRedeal = false,
      dealOrder = DealOrder.RowsLeftToRightTopToBottom
    )
  ),
  complete = false
)

