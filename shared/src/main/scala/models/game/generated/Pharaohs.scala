package models.game.generated

import models.game._
import models.game.rules._

// scalastyle:off
object Pharaohs extends GameRules(
  id = "pharaohs",
  title = "Pharaohs",
  description = "A variation of ^pyramid^ with three pyramids.",
  cardRemovalMethod = CardRemovalMethod.RemovePairsAddingToThirteenOrK,
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
      offscreen = true,
      autoMoveCards = true,
      autoMoveFrom = Seq("Stock", "Pyramid", "Waste", "Pocket", "Reserve", "Cell", "Foundation", "Tableau")
    )
  ),
  pyramids = Seq(
    PyramidRules(
      name = "Pyramid",
      pyramidType = PyramidType.Standard,
      height = 7,
      cardsFaceDown = PyramidFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      rankMatchRuleForBuilding = RankMatchRule.Down,
      wrapFromKingToAce = false,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      rankMatchRuleForMovingStacks = RankMatchRule.Down,
      mayMoveToNonEmptyFrom = Seq("Waste", "Tableau", "Pyramid", "Foundation"),
      mayMoveToEmptyFrom = Seq("Waste", "Tableau", "Pyramid", "Foundation"),
      emptyFilledWith = PyramidFillEmptyWith.None
    ),
    PyramidRules(
      name = "Pyramid",
      pyramidType = PyramidType.Standard,
      height = 7,
      cardsFaceDown = PyramidFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      rankMatchRuleForBuilding = RankMatchRule.Down,
      wrapFromKingToAce = false,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      rankMatchRuleForMovingStacks = RankMatchRule.Down,
      mayMoveToNonEmptyFrom = Seq("Waste", "Tableau", "Pyramid", "Foundation"),
      mayMoveToEmptyFrom = Seq("Waste", "Tableau", "Pyramid", "Foundation"),
      emptyFilledWith = PyramidFillEmptyWith.None
    ),
    PyramidRules(
      name = "Pyramid",
      pyramidType = PyramidType.Standard,
      height = 7,
      cardsFaceDown = PyramidFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      rankMatchRuleForBuilding = RankMatchRule.Down,
      wrapFromKingToAce = false,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      rankMatchRuleForMovingStacks = RankMatchRule.Down,
      mayMoveToNonEmptyFrom = Seq("Waste", "Tableau", "Pyramid", "Foundation"),
      mayMoveToEmptyFrom = Seq("Waste", "Tableau", "Pyramid", "Foundation"),
      emptyFilledWith = PyramidFillEmptyWith.None
    )
  ),
  complete = false
)
// scalastyle:on

