// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Stronghold extends GameRules(
  id = "stronghold",
  title = "Stronghold",
  like = Some("streetsandalleys"),
  description = "A variation of ^beleagueredcastle^ with one ^freecell^ style cell added.",
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
  cells = Some(
    CellRules(
      numPiles = 1
    )
  ),
  complete = false
)

