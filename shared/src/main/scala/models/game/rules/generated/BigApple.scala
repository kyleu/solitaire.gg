// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object BigApple extends GameRules(
  id = "bigapple",
  title = "Big Apple",
  description = "A difficult variation of ^newyork^ with three cells instead of three waste piles, but where stacks can be moved.",
  deckOptions = DeckOptions(
    numDecks = 2,
    lowRank = Some(Rank.Unknown)
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
      wrapFromKingToAce = true,
      canMoveFrom = FoundationCanMoveFrom.Never,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      emptyFilledWith = TableauFillEmptyWith.Aces,
      mayMoveToEmptyFrom = Seq("Stock", "Cell")
    )
  ),
  cells = Some(
    CellRules(

      numPiles = 3
    )
  ),
  complete = false
)

