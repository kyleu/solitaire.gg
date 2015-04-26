package models.game.generated

import models.game._
import models.game.rules._

// scalastyle:off
object PatientPairs extends GameRules(
  id = "patientpairs",
  title = "Patient Pairs",
  description = "As in ^simplepairs^, you remove pairs of cards of the same rank, but the cards start out all dealt to the tableau, so a smidgeon more skill is involved.",
  cardRemovalMethod = CardRemovalMethod.RemovePairsOfSameRank,
  foundations = Seq(
    FoundationRules(
      name = "Foundation",
      numPiles = 4,
      lowRank = FoundationLowRank.AnyCard,
      initialCards = InitialCards.Count(0),
      suitMatchRule = SuitMatchRule.SameSuit,
      rankMatchRule = RankMatchRule.Up,
      wrapFromKingToAce = true,
      moveCompleteSequencesOnly = false,
      maxCards = 0,
      canMoveFrom = FoundationCanMoveFrom.Never,
      mayMoveToFrom = Seq("Stock", "Pyramid", "Waste", "Pocket", "Reserve", "Cell", "Foundation", "Tableau"),
      offscreen = true,
      autoMoveCards = true,
      autoMoveFrom = Seq("Stock", "Pyramid", "Waste", "Pocket", "Reserve", "Cell", "Foundation", "Tableau")
    )
  ),
  tableaus = Seq(
    TableauRules(
      name = "Tableau",
      numPiles = 13,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      rankMatchRuleForBuilding = RankMatchRule.Down,
      wrapFromKingToAce = false,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      rankMatchRuleForMovingStacks = RankMatchRule.Down,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Nowhere,
      emptyFilledWith = TableauFillEmptyWith.None,
      mayMoveToNonEmptyFrom = Seq("Stock", "Pyramid", "Waste", "Pocket", "Reserve", "Cell", "Foundation", "Tableau"),
      mayMoveToEmptyFrom = Seq("Stock", "Pyramid", "Waste", "Pocket", "Reserve", "Cell", "Foundation", "Tableau"),
      maxCards = 0,
      actionDuringDeal = PileAction.None,
      actionAfterDeal = PileAction.None,
      pilesWithLowCardsAtBottom = 0
    )
  ),
  complete = false
)
// scalastyle:on

