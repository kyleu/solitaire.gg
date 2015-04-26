package models.game.generated

import models.game._
import models.game.rules._

// scalastyle:off
object DarkPyramid extends GameRules(
  id = "darkpyramid",
  title = "Dark Pyramid",
  description = "A version of ^pyramid^ where the cards are dealt face down.",
  cardRemovalMethod = CardRemovalMethod.RemovePairsAddingToThirteenOrK,
  stock = Some(
    StockRules(
      name = "Stock",
      dealTo = StockDealTo.WasteOrPairManually,
      maximumDeals = Some(3),
      cardsDealt = StockCardsDealt.Count(1),
      stopAfterPartialDeal = true,
      createPocketWhenEmpty = false,
      galleryMode = false
    )
  ),
  waste = Some(
    WasteRules(
      name = "Waste",
      numPiles = 1,
      playableCards = WastePlayableCards.TopCardOnly
    )
  ),
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
      cardsFaceDown = PyramidFaceDownCards.AllButLastRow,
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

