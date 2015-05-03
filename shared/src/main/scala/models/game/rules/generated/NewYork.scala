// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object NewYork extends GameRules(
  id = "newyork",
  title = "New York",
  description = "In this variation of ^dover^, you can choose which of the three waste piles you play cards from the stock onto, which is good beca" +
  "use it's hard to rearrange things much on the tableau.",
  deckOptions = DeckOptions(
    numDecks = 2,
    lowRank = Rank.Unknown
  ),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Manually,
      maximumDeals = Some(1)
    )
  ),
  waste = Some(
    WasteRules(
      numPiles = 3
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Aces,
      mayMoveToEmptyFrom = Seq("Stock", "Waste")
    )
  ),
  complete = false
)

