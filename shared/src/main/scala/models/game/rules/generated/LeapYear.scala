// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object LeapYear extends GameRules(
  id = "leapyear",
  title = "Leap Year",
  description = "A four-deck version of ^auldlangsyne^. Requires a large screen.",
  deckOptions = DeckOptions(
    numDecks = 4
  ),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Tableau,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 16,
      initialCards = InitialCards.PileIndex,
      suitMatchRule = SuitMatchRule.Any,
      wrapFromKingToAce = true,
      canMoveFrom = FoundationCanMoveFrom.Never
    )
  ),
  tableaus = Seq(
    TableauRules(
      name = "Reserve",
      numPiles = 8,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  complete = false
)

