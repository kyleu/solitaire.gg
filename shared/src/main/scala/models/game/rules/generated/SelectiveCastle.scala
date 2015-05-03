// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object SelectiveCastle extends GameRules(
  id = "selectivecastle",
  title = "Selective Castle",
  like = Some("beleagueredcastle"),
  description = "A version of ^beleagueredcastle^ where the base of the foundation is determined by the first card you play to it.",
  deckOptions = DeckOptions(
    lowRank = Rank.Unknown
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)

