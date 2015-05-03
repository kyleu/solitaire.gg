// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object TrustyTwelve extends GameRules(
  id = "trustytwelve",
  title = "Trusty Twelve",
  related = Seq("bunker", "knottynines", "sweetsixteen", "upandup"),
  description = "More luck than skill is needed to win this game of building sequences on the tableau.",
  victoryCondition = VictoryCondition.NoneInStock,
  stock = Some(
    StockRules(
      cardsShown = 19,
      dealTo = StockDealTo.Never,
      maximumDeals = Some(1)
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 12,
      cardsShown = 2,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)

