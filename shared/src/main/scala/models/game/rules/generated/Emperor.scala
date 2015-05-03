// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Emperor extends GameRules(
  id = "emperor",
  title = "Emperor",
  like = Some("rankandfile"),
  description = "A more difficult version of ^rankandfile^ where only single cards can be moved..",
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
      numPiles = 10,
      initialCards = InitialCards.Count(4),
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)

