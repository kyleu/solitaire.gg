// Generated rules for Scalataire.
package models.game.generated

import models.game._
import models.game.rules._

object BatsfordAgain extends GameRules(
  id = "batsfordagain",
  title = "Batsford Again",
  description = "A variation of ^batsford^ with a redeal.",
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
      canMoveFrom = FoundationCanMoveFrom.Never,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      emptyFilledWith = TableauFillEmptyWith.Kings
    ),
    TableauRules(
      name = "Reserve",
      setNumber = 1,
      numPiles = 1,
      initialCards = InitialCards.Count(0),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      rankMatchRuleForBuilding = RankMatchRule.Equal,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Kings,
      maxCards = 3
    )
  ),
  complete = false
)

