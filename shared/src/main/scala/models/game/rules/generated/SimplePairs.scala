// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object SimplePairs extends GameRules(
  id = "simplepairs",
  title = "Simple Pairs",
  related = Seq("blockten", "crisscross", "doubletcell", "doublets", "eighteens", "patientpairs", "straightfifteens"),
  description = "A game where you remove pairs of cards of the same rank. Bring your luck, not your brain, to this game.",
  cardRemovalMethod = CardRemovalMethod.RemovePairsOfSameRank,
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Never,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      lowRank = FoundationLowRank.AnyCard,
      wrapFromKingToAce = true,
      maxCards = 0,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 9,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock
    )
  ),
  complete = false
)

