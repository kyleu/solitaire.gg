// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object SuitsUp extends GameRules(
  id = "suitsup",
  title = "Suits Up",
  description = "An easy game where you remove pairs of cards of the same suit, until only four cards are left.",
  victoryCondition = VictoryCondition.AllButFourCardsOnFoundation,
  cardRemovalMethod = CardRemovalMethod.RemovePairsOfSameSuit,
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Tableau,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      canMoveFrom = FoundationCanMoveFrom.Never,
      visible = false,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 4,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  complete = false
)

