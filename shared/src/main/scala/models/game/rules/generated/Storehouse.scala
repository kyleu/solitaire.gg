// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Storehouse extends GameRules(
  id = "storehouse",
  title = "Storehouse",
  description = "A old ^canfield^ variant first described in 1939. A pleasant game, but there is scarcely any strategy required.",
  deckOptions = DeckOptions(
    lowRank = Rank.Two
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(3)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      initialCards = 4,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 4,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Reserve,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  reserves = Some(
    ReserveRules(
      name = "Storehouse",
      numPiles = 1,
      initialCards = 13,
      cardsFaceDown = 100
    )
  ),
  complete = false
)

