// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Roman extends GameRules(
  id = "roman",
  title = "Roman",
  description = "A variation of ^signora^ where we build regardless of suit. Invented by Thomas Warfield.",
  deckOptions = DeckOptions(
    numDecks = 2,
    lowRank = Some(Rank.Unknown)
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
      canMoveFrom = FoundationCanMoveFrom.Never
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 9,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.WasteThenStock,
      emptyFilledWith = TableauFillEmptyWith.Aces,
      mayMoveToNonEmptyFrom = Seq("Stock", "Pyramid", "Waste", "Pocket", "Cell", "Foundation", "Tableau"),
      mayMoveToEmptyFrom = Seq("Stock", "Pyramid", "Waste", "Pocket", "Cell", "Foundation", "Tableau")
    )
  ),
  reserves = Some(
    ReserveRules(
      name = "Reserve",
      numPiles = 1,
      initialCards = 11,
      cardsFaceDown = 0
    )
  ),
  complete = false
)

