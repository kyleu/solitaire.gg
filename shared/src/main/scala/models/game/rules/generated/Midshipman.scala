// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Midshipman extends GameRules(
  id = "midshipman",
  title = "Midshipman",
  like = Some("maria"),
  description = "A slightly easier variation of ^maria^ where we build by different suits instead of alternate colors and where some cards start ou" +
  "t face down.",
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
      numPiles = 9,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(2),
      suitMatchRuleForBuilding = SuitMatchRule.DifferentSuits,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)

