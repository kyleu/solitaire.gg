// Generated rules for Scalataire.
package models.game.generated

import models.game._
import models.game.rules._

object Gloucestershire extends GameRules(
  id = "gloucestershire",
  title = "Gloucestershire",
  description = "A two-deck variation of ^flowergarden^.",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      maxCards = 26,
      canMoveFrom = FoundationCanMoveFrom.Never,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Custom,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      wrapFromKingToAce = true,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)

