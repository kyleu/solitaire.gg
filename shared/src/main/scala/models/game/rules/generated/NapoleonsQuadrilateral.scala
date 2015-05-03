// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object NapoleonsQuadrilateral extends GameRules(
  id = "napoleonsquadrilateral",
  title = "Napoleon's Quadrilateral",
  like = Some("fortythieves"),
  description = "This older, more difficult, version of ^napoleonssquare^ does not allow stack moves, but moves a lot of cards to the foundation du" +
  "ring the deal.",
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
      initialCards = 8,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 12,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Kings,
      actionDuringDeal = PileAction.MoveToFoundation
    )
  ),
  complete = false
)

