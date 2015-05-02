// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object CastleMount extends GameRules(
  id = "castlemount",
  title = "Castle Mount",
  description = "A three-deck version of ^beleagueredcastle^ invented by Thomas Warfield. Since kings can only be moved to empty spaces or the foun" +
  "dation, opening up some columns is the key to the game, except there are 12 cards in each column that need to be gotten out of the" +
  " way first.",
  deckOptions = DeckOptions(
    numDecks = 3
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 12,
      initialCards = 12,
      wrapFromKingToAce = true,
      canMoveFrom = FoundationCanMoveFrom.Never,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 12,
      initialCards = InitialCards.Count(12),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)

