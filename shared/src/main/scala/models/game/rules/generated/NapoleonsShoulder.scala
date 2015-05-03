// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object NapoleonsShoulder extends GameRules(
  id = "napoleonsshoulder",
  title = "Napoleon's Shoulder",
  description = "A varient of ^napoleonssquare^ where you build regardless of suit.",
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
      numPiles = 12,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Aces,
      mayMoveToEmptyFrom = Seq("Waste")
    )
  ),
  complete = false
)

