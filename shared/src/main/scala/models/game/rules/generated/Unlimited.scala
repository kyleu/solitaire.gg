// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Unlimited extends GameRules(
  id = "unlimited",
  title = "Unlimited",
  description = "An easier variant of ^interchange^, in which we are allowed unlimited redeals",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(StockRules()),
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
      cardsFaceDown = TableauFaceDownCards.EvenNumbered,
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  complete = false
)

