// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Wood extends GameRules(
  id = "wood",
  title = "Wood",
  description = "A game where we build both the foundation and the tableau in alternate colors. The big problem is the ten-card reserve, which can " +
  "be played only to the foundation, which generally requires some advanced planning to achieve.",
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
      initialCards = 1,
      suitMatchRule = SuitMatchRule.AlternatingColors,
      wrapFromKingToAce = true,
      canMoveFrom = FoundationCanMoveFrom.Never,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Aces,
      mayMoveToNonEmptyFrom = Seq("Stock", "Pyramid", "Waste", "Pocket", "Cell", "Foundation", "Tableau"),
      mayMoveToEmptyFrom = Seq("Waste")
    )
  ),
  reserves = Some(
    ReserveRules(
      name = "Reserve",
      numPiles = 1,
      initialCards = 10,
      cardsFaceDown = 0
    )
  ),
  complete = false
)

