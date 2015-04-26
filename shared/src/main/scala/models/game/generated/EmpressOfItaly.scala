// Generated 2015-04-26 for Scalataire.
package models.game.generated

import models.game._
import models.game.rules._

// scalastyle:off
object EmpressOfItaly extends GameRules(
  id = "empressofitaly",
  title = "Empress of Italy",
  description = "A four-deck version of ^blondesandbrunettes^ invented by Thomas Warfield.",
  deckOptions = DeckOptions(
    numDecks = 4,
    lowRank = Some(Rank.Unknown)
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(
    WasteRules(

    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 16,
      initialCards = InitialCards.Count(1),
      suitMatchRule = SuitMatchRule.AlternatingColors,
      wrapFromKingToAce = true,
      canMoveFrom = FoundationCanMoveFrom.Never
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 11,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.StockThenWaste,
      emptyFilledWith = TableauFillEmptyWith.Aces,
      mayMoveToNonEmptyFrom = Seq("Stock", "Pyramid", "Waste", "Pocket", "Cell", "Foundation", "Tableau"),
      mayMoveToEmptyFrom = Seq("Stock", "Pyramid", "Waste", "Pocket", "Cell", "Foundation", "Tableau")
    )
  ),
  reserves = Some(
    ReserveRules(
      name = "Reserve",
      numPiles = 1,
      initialCards = 19,
      cardsFaceDown = 0
    )
  ),
  complete = false
)
// scalastyle:on

