// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Lower48 extends GameRules(
  id = "lower48",
  title = "Lower 48",
  like = Some("fortyandeight"),
  description = "A variation of ^fortyandeight^ where you build in alternate colors instead of in the same suit.",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(2)
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
      initialCards = InitialCards.Count(5),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)

