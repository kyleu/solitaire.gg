// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object DoubleLimited extends GameRules(
  id = "doublelimited",
  title = "Double Limited",
  description = "A four-deck version of ^limited^. A large screen will be needed.",
  deckOptions = DeckOptions(
    numDecks = 4
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 16,
      wrapFromKingToAce = true,
      canMoveFrom = FoundationCanMoveFrom.Never,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 16,
      initialCards = InitialCards.Count(3),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)

