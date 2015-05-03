// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object TripleInterchange extends GameRules(
  id = "tripleinterchange",
  title = "Triple Interchange",
  description = "A three-deck version of ^interchange^.",
  deckOptions = DeckOptions(
    numDecks = 3
  ),
  stock = Some(StockRules()),
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
      numPiles = 9,
      initialCards = InitialCards.Count(9),
      cardsFaceDown = TableauFaceDownCards.EvenNumbered,
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)

