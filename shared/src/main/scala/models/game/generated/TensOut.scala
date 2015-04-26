package models.game.generated

import models.game._
import models.game.rules._

// scalastyle:off
object TensOut extends GameRules(
  id = "tensout",
  title = "Tens Out",
  description = "A variation of ^fourteenout^ where we remove pairs adding to 10.",
  cardRemovalMethod = CardRemovalMethod.RemovePairsAddingToTenOr10PairOrJPairOrQPairOrKPair,
  foundations = Seq(
    FoundationRules(
      name = "Foundation",
      numPiles = 4,
      lowRank = FoundationLowRank.DeckLowRank,
      initialCards = InitialCards.Count(0),
      suitMatchRule = SuitMatchRule.SameSuit,
      rankMatchRule = RankMatchRule.Up,
      wrapFromKingToAce = true,
      moveCompleteSequencesOnly = false,
      maxCards = -1,
      canMoveFrom = FoundationCanMoveFrom.Never,
      mayMoveToFrom = Seq("Stock", "Pyramid", "Waste", "Pocket", "Reserve", "Cell", "Foundation", "Tableau"),
      offscreen = false,
      autoMoveCards = true,
      autoMoveFrom = Seq("Stock", "Pyramid", "Waste", "Pocket", "Reserve", "Cell", "Foundation", "Tableau")
    )
  ),
  tableaus = Seq(
    TableauRules(
      name = "Tableau",
      numPiles = 12,
      initialCards = InitialCards.RestOfDeck,
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

