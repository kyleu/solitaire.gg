// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Cleopatra extends GameRules(
  id = "cleopatra",
  title = "Cleopatra",
  description = "Thomas Warfield's variant of ^fortythieves^ with a pyramid-shaped tableau.",
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
      canMoveFrom = FoundationCanMoveFrom.Never,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 13,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "U",
        "DU",
        "UDU",
        "DUDU",
        "UDUDU",
        "DUDUDU",
        "UDUDUDU",
        "DUDUDU",
        "UDUDU",
        "DUDU",
        "UDU",
        "DU",
        "U"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)

