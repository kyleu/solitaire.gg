// Generated 2015-04-26 for Scalataire.
package models.game.generated

import models.game._
import models.game.rules._

// scalastyle:off
object DoubleAcesAndKings extends GameRules(
  id = "doubleacesandkings",
  title = "Double Aces and Kings",
  description = "A four-deck version of ^acesandkings^ invented by Thomas Warfield.",
  deckOptions = DeckOptions(
    numDecks = 4
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
      name = "Ace Foundation",
      numPiles = 8,
      suitMatchRule = SuitMatchRule.Any,
      wrapFromKingToAce = true
    ),
    FoundationRules(
      name = "King Foundation",
      numPiles = 8,
      lowRank = FoundationLowRank.DeckHighRank,
      suitMatchRule = SuitMatchRule.Any,
      rankMatchRule = RankMatchRule.Down,
      wrapFromKingToAce = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  reserves = Some(
    ReserveRules(
      name = "Reserve",
      numPiles = 4,
      initialCards = 13,
      cardsFaceDown = 0
    )
  ),
  complete = false
)
// scalastyle:on

