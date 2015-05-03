// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object LittleNapolean extends GameRules(
  id = "littlenapoleon",
  title = "Little Napolean",
  like = Some("fortythieves"),
  related = Seq("mcclellan"),
  description = "A ^fortythieves^ variant that shows some ^spider^ influences, because you can build regardless of suit, but only move same-suit se" +
  "quences.",
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
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(4),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)

