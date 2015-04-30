// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object FortyNine extends GameRules(
  id = "fortynine",
  title = "Forty-Nine",
  description = "This ^fortyandeight^ variation has forty-nine cards in a seven by seven tableau. You build down regardless of suit, moving cards o" +
  "ne at a time.",
  deckOptions = DeckOptions(
    numDecks = 2
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
      wrapFromKingToAce = true,
      canMoveFrom = FoundationCanMoveFrom.Never,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      initialCards = InitialCards.Count(7),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)

