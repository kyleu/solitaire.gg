// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Darwin extends GameRules(
  id = "darwin",
  title = "Darwin",
  description = "A three-deck version of ^australian^ Solitaire, which is a cross between ^yukon^ and ^klondike^.",
  deckOptions = DeckOptions(
    numDecks = 3
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 12,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Count(8),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      emptyFilledWith = TableauFillEmptyWith.KingsUntilStockEmpty
    )
  ),
  complete = false
)

