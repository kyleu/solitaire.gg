// Generated 2015-04-26 for Scalataire.
package models.game.generated

import models.game._
import models.game.rules._

// scalastyle:off
object Tournament extends GameRules(
  id = "tournament",
  title = "Tournament",
  description = "A two-deck game where no building is allowed on the tableau, and you must rely on eight cells to move your cards to the foundation.",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Tableau,
      maximumDeals = Some(1),
      cardsDealt = StockCardsDealt.Count(4)
    )
  ),
  foundations = Seq(
    FoundationRules(
      name = "Ace Foundation",
      numPiles = 4,
      wrapFromKingToAce = true
    ),
    FoundationRules(
      name = "King Foundation",
      numPiles = 4,
      lowRank = FoundationLowRank.DeckHighRank,
      rankMatchRule = RankMatchRule.Down,
      wrapFromKingToAce = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 6,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      rankMatchRuleForBuilding = RankMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock,
      mayMoveToNonEmptyFrom = Seq("Cell", "Tableau")
    )
  ),
  cells = Some(
    CellRules(

      numPiles = 8,
      canMoveFrom = Seq("Tableau"),
      initialCards = 8
    )
  ),
  complete = false
)
// scalastyle:on

